package ru.sigma.matchmaking.service

import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Service
import ru.sigma.common.context.UserAuthContextHolder
import ru.sigma.common.dto.RoomDto
import ru.sigma.data.domain.entity.RoomEntity
import ru.sigma.data.domain.entity.UserEntity
import ru.sigma.data.extensions.RoomExtensions.toDto
import ru.sigma.data.repository.RoomRepository
import ru.sigma.data.repository.UserRepository
import ru.sigma.game.domain.dto.GameDto
import ru.sigma.game.service.GameService
import ru.sigma.matchmaking.exception.RoomNotFoundException

@Service
class MatchMakingService(
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository,
    private val gameService: GameService,
    private val shipPlacementService: ShipPlacementService
) {
    fun createRoom(): RoomDto {
        val user = getCurrentUserOrThrow()

        val entity = RoomEntity(
            code = generateCode(),
            host = user,
            maxSize = 2
        )

        return roomRepository.save(entity).toDto()
    }

    fun getRoom(code: String): RoomDto {
        val room = getRoomOrThrow(code)
        return room.toDto()
    }

    fun joinRoom(code: String): RoomDto {
        val room = getRoomOrThrow(code)
        val user = getCurrentUserOrThrow()

        check(room.players.size < room.maxSize) {
            "Room is full"
        }

        room.players.add(user)
        roomRepository.save(room)

        return room.toDto()
    }

    fun joinRandomRoom(): RoomDto {
        val room = roomRepository.findByIsPublic(true).randomOrNull()
            ?: throw RoomNotFoundException(ROOM_NOT_FOUND_MESSAGE)

        return joinRoom(room.code)
    }

    fun startGame(code: String): GameDto {
        val room = getRoomOrThrow(code)
        val players = (room.players + room.host).shuffled()
        val states = players.associate { Pair(it.id, shipPlacementService.getPlaceShips()) }

        return gameService.startNewGame(states, 10)
    }


    fun leaveRoom(code: String) {
        val room = getRoomOrThrow(code)

        val user = getCurrentUserOrThrow()

        if (room.host.id == user.id) {
            roomRepository.delete(room)
            return
        }

        room.players.remove(user)
        roomRepository.save(room)
    }

    private fun getRoomOrThrow(code: String): RoomEntity {
        return roomRepository.findById(code).getOrNull()
            ?: throw RoomNotFoundException(ROOM_NOT_FOUND_MESSAGE)
    }

    private fun getCurrentUserOrThrow(): UserEntity {
        val userInfo = UserAuthContextHolder.get()
        return userRepository.findById(userInfo.id).getOrNull()
            ?: throw IllegalStateException("User not found")
    }

    private fun generateCode(length: Int = 10): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    companion object {
        private const val ROOM_NOT_FOUND_MESSAGE = "Room not found"
    }
}