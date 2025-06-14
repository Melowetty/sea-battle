package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "users")
open class UserEntity(
    @Id
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "telegram_id", nullable = false, unique = true)
    val telegramId: Long,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "avatar")
    val avatar: String? = null,

    @Column(name = "is_bot", nullable = false)
    val isBot: Boolean,

    @Embedded
    val statistic: StatisticEntity= StatisticEntity(),

    @ManyToMany
    @JoinTable(
        name = "game_user",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "game_id")]
    )
    val games: MutableSet<GameEntity> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "game_result_user",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "game_result_id")]
    )
    val gameResults: MutableSet<GameResultEntity> = mutableSetOf()


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntity) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}




