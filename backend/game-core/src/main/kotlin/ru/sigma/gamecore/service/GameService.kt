package ru.sigma.gamecore.service

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.context.ApplicationEventPublisher
import java.time.Instant
import java.util.UUID
import org.springframework.stereotype.Service
import ru.sigma.common.model.Coordinate
import ru.sigma.data.domain.entity.GameEntity
import ru.sigma.data.domain.entity.GameResultEntity
import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.repository.GameRepository
import ru.sigma.data.repository.GameResultRepository
import ru.sigma.data.repository.UserRepository
import ru.sigma.gamecore.domain.dto.GameDto
import ru.sigma.gamecore.domain.dto.PlayerDto
import ru.sigma.gamecore.domain.dto.ShotResultDto
import ru.sigma.gamecore.domain.model.BotTurnEvent
import java.util.Timer
import java.util.TimerTask

@Service
class GameService(
    private val initService: InitService,
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper,
    private val shotService: ShotService,
    private val gameResultRepository: GameResultRepository,
    private val eventPublisher: ApplicationEventPublisher
) {

    fun startNewGame(
        gameId: Long,
        players: Map<UUID, List<List<Coordinate>>>,
        size: Int
    ) {
        val state = initService.initGameState(gameId, players, size)
        val stateAsString = objectMapper.writeValueAsString(state)

        val game = GameEntity(
            id = gameId,
            state = stateAsString,
            createdAt = Instant.now(),
            players = userRepository.findAllById(players.keys.toList()).toMutableSet()
        )

        gameRepository.save(game)

        checkBotTurn(gameId, state)
    }

    fun getCurrentGameInfo(
        gameId: Long,
        userId: UUID
    ): GameDto {
        return createGameDto(
            gameId = gameId,
            userId = userId
        )
    }

    fun processTheShot(
        gameId: Long,
        shot: Coordinate
    ): ShotResultDto {
        val gameState = loadGameState(gameId) // полуеаем текущее состояние игры по id

        val result = shotService.checkShot(gameId, gameState, shot) // делаем выстрел и получаем результат

        calculateNextMove(gameId,  result.event, gameState)

        return result // возвращаем рещультат выстрела
    }

    fun processTheVictory(
        gameId: Long,
        gameState: GameState,
    ) {
        val game: GameEntity = gameRepository.findById(gameId)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $gameId") }
        val winner = userRepository.findById(getCurrentUser(gameState))
            .orElseThrow { EntityNotFoundException("Entity not found with id: ${getCurrentUser(gameState)}") }

        gameResultRepository.save(GameResultEntity(
            id =  gameId,
            winner = winner,
            startedAt = game.createdAt,
            endAt = Instant.now(),
            players = game.players,
        ))
        gameRepository.deleteById(gameId)

    }

    private fun getCurrentUser(game: GameState): UUID = game.players[1 - (game.round % 2)]

    private fun calculateNextMove(
        gameId: Long,
        event: Event,
        gameState: GameState
    ) {
        when (event) {
            Event.ALL_DESTRUCTION -> processTheVictory(gameId, gameState) // если текущий игрок всех победил
            Event.MISS -> { // если был промах, то раунд сменится
                gameState.round++ // перееход на следующий раунд, соответвенно ход другого игрока
                checkBotTurn(gameId, gameState)
            }
            else -> print("event not found")
        }
    }

    private fun checkBotTurn(
        gameId: Long,
        gameState: GameState
    ) {
        val nextRoundUserId = gameState.players[1 - (gameState.round % 2)]
        val currentPlayer = userRepository.findById(nextRoundUserId)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $nextRoundUserId") }

        if (currentPlayer.isBot) { // проверяем не ход ли бота сейчас
            callBot(gameId, nextRoundUserId) //вызываем ход бота
        }

    }

    private fun callBot(
        gameId: Long,
        botId: UUID
    ) {
        val gameState = loadGameState(gameId)
        val targetPlayer = gameState.players[(gameState.round % 2)]
        val targetPlayerField = gameState.playersFields.getValue(targetPlayer)
        val size = gameState.fieldSize
        Timer().schedule(object : TimerTask() {
            override fun run() {
                eventPublisher.publishEvent(BotTurnEvent(targetPlayerField, size, botId))
            }
        }, 5000)
    }

    private fun loadGameState(
        gameId: Long
    ): GameState {
        val game = gameRepository.findById(gameId)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $gameId") }

        val gameState = objectMapper.readValue(game.state, GameState::class.java)

        return gameState
    }

    private fun createGameDto(gameId: Long, userId: UUID): GameDto {
        val game = gameRepository.findById(gameId)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $gameId") }

        val gameState = loadGameState(gameId)
        val currentPlayer = gameState.players[1 - (gameState.round % 2)]
        val playerState = gameState.playersFields[userId]
            ?: throw IllegalStateException("Player state is null")

        val players = game.players.map { user ->
            PlayerDto(name = user.name, avatar = user.avatar)
        }

        return GameDto(
            players = players,
            currentPlayer = currentPlayer,
            playerState = playerState,
            gameStartDate = game.createdAt
        )

    }
}