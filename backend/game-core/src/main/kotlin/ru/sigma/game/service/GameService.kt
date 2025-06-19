package ru.sigma.game.service

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.EntityNotFoundException
import java.time.Instant
import java.util.UUID
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.sigma.botengine.service.BotTurnService
import ru.sigma.common.context.UserAuthContextHolder
import ru.sigma.common.model.Coordinate
import ru.sigma.data.domain.entity.GameEntity
import ru.sigma.data.domain.entity.GameResultEntity
import ru.sigma.data.domain.entity.UserEntity
import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.game.BotShootingState
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.extensions.UserExtensions.toDto
import ru.sigma.data.repository.GameRepository
import ru.sigma.data.repository.GameResultRepository
import ru.sigma.data.repository.UserRepository
import ru.sigma.game.domain.dto.AfterShotStateDto
import ru.sigma.game.domain.dto.GameDto
import ru.sigma.game.domain.dto.ShotResultDto
import ru.sigma.game.domain.exception.GameNotFoundException

@Service
class GameService(
    private val initService: InitService,
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper,
    private val shotService: ShotService,
    private val gameResultRepository: GameResultRepository,
    private val botService: BotTurnService
) {

    fun startNewGame(
        players: Map<UUID, List<List<Coordinate>>>,
        size: Int
    ): GameDto {
        val state = initService.initGameState(players, size)
        val stateAsString = objectMapper.writeValueAsString(state)

        val game = GameEntity(
            state = stateAsString,
            createdAt = Instant.now(),
            players = userRepository.findAllById(players.keys.toList()).toMutableSet()
        )

        val entity = gameRepository.save(game)

        checkBotTurn(entity.id, state)

        return entity.toDto(getCurrentUserOrThrow().id)
    }

    fun getGameInfo(
        gameId: Long
    ): GameDto {
        val user = getCurrentUserOrThrow()
        val game = getGameOrThrow(gameId)

        return game.toDto(user.id)
    }

    fun processTheShot(
        gameId: Long,
        shot: Coordinate
    ): AfterShotStateDto {
        val gameState = loadGameState(gameId) // полуеаем текущее состояние игры по id

//        if (getCurrentUser(gameState) != getCurrentUserOrThrow().id) {
//            return AfterShotStateDto(
//                event = Event.FILL,
//                targetPlayer = getCurrentUser(gameState),
//                nextPlayer = getCurrentUser(gameState),
//                fieldsDifference = listOf()
//            )
//        }

        val result = shotService.makeShot(gameId, gameState, shot) // делаем выстрел и получаем результат

        calculateNextMove(gameId,  result.afterShotState.event, gameState)

        saveGameState(gameId, result.gameState)

        return result.afterShotState // возвращаем рещультат выстрела
    }

    fun leave(
        gameId: Long
    ) {
        val looser = getCurrentUserOrThrow()
        val game = getGameOrThrow(gameId)
        val winner = game.players.firstOrNull { it != looser}
            ?: throw IllegalStateException("User not found")
        processTheVictory(gameId, winner)
    }

    @Transactional
    fun processTheVictory(
        gameId: Long,
        winner: UserEntity
    ) {
        val game = getGameOrThrow(gameId)

        gameResultRepository.save(GameResultEntity(
                id =  gameId,
                winner = winner,
                startedAt = game.createdAt,
                endAt = Instant.now(),
                players = game.players,
            )
        )
        gameRepository.deleteById(gameId)
    }

    private fun getCurrentUser(game: GameState): UUID = game.players[1 - (game.round % 2)]

    private fun calculateNextMove(
        gameId: Long,
        event: Event,
        gameState: GameState
    ) {
        if (event == Event.ALL_DESTRUCTION) { // если текущий игрок всех победил
            val winner = userRepository.findById(getCurrentUser(gameState))
                .orElseThrow { EntityNotFoundException("Entity not found with id: ${getCurrentUser(gameState)}") }
            processTheVictory(gameId, winner)
        }
        else {
            if (event == Event.MISS) { // если был промах, то раунд сменится
                gameState.round++ // перееход на следующий раунд, соответвенно ход другого игрока
                checkBotTurn(gameId, gameState)
            }
        }
    }

    private fun checkBotTurn(
        gameId: Long,
        gameState: GameState
    ) {
        val thisPlayer = gameState.players[gameState.round % 2]
        val thisPlayerState = gameState.playersFields[thisPlayer]
            ?: throw IllegalStateException("Player state is null")
        val nextRoundUserId = gameState.players[1 - (gameState.round % 2)]
        val currentPlayer = userRepository.findById(nextRoundUserId)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $nextRoundUserId") }

        if (currentPlayer.isBot) { // проверяем не ход ли бота сейчас
            val fieldInfo = BotShootingState(
                misses = thisPlayerState.misses + thisPlayerState.destructions,
                hits = thisPlayerState.hits,
                fieldSize = gameState.fieldSize
            )
            makeBotShot(gameId, gameState, fieldInfo, nextRoundUserId) //вызываем ход бота
        }

    }

    private fun makeBotShot(
        gameId: Long,
        gameState: GameState,
        botShootingState: BotShootingState,
        botId: UUID
    ) {
        Thread {
            Thread.sleep(5000)
            val coords = botService.processShot(botShootingState, botId)
            var result = shotService.makeShot(gameId, gameState, coords)
            saveGameState(gameId, result.gameState)

            while (result.afterShotState.event == Event.HIT || result.afterShotState.event == Event.DESTRUCTION) {
                Thread.sleep(5000)
                val newCoords = botService.processShot(botShootingState, botId)
                result = shotService.makeShot(gameId, gameState, newCoords)
                saveGameState(gameId, result.gameState)
            }
        }.start()
    }

    private fun loadGameState(
        gameId: Long
    ): GameState {
        val game = getGameOrThrow(gameId)

        val gameState = objectMapper.readValue(game.state, GameState::class.java)

        return gameState
    }

    private fun saveGameState(
        gameId: Long,
        updatedState: GameState
    ) {
        val game = getGameOrThrow(gameId) // Получаем существующую сущность

        // Сериализуем обновлённое состояние в JSON
        val serializedState = objectMapper.writeValueAsString(updatedState)

        // Обновляем и сохраняем
        game.state = serializedState
        gameRepository.save(game) // Предполагается, что у вас есть JPA репозиторий
    }

    private fun GameEntity.toDto(userId: UUID): GameDto {
        val gameState = objectMapper.readValue(state, GameState::class.java)
        val currentPlayer = gameState.players[1 - (gameState.round % 2)]
        val playerState = gameState.playersFields[userId]
            ?: throw IllegalStateException("Player state is null")

        val players = players.map { user ->
            user.toDto()
        }

        return GameDto(
            id = id,
            players = players,
            currentPlayer = currentPlayer,
            playerState = playerState,
            gameStartDate = createdAt
        )
    }

    private fun getGameOrThrow(gameId: Long): GameEntity {
        return gameRepository.findById(gameId).getOrNull()
            ?: throw GameNotFoundException("Game not found")
    }

    private fun getCurrentUserOrThrow(): UserEntity {
        val userInfo = UserAuthContextHolder.get()
        return userRepository.findById(userInfo.id).getOrNull()
            ?: throw IllegalStateException("User not found")
    }

}