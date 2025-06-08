package ru.sigma.data.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
open class UsersEntity(
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID,

    @Column(name = "telegram_id", nullable = false, unique = true)
    val telegramId: Long,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "avatar")
    val avatar: String? = null,

    @Column(name = "is_bot", nullable = false)
    val isBot: Boolean,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    val statistic: StatisticEntity? = null,

    @ManyToMany
    @JoinTable(
        name = "game_user",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "game_id")]
    )
    val games: MutableSet<GameEntity> = mutableSetOf()
)




