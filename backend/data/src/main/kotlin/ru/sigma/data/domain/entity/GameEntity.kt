package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "game")
class GameEntity(
    id: Long = 0L,

    @Column(name = "state", columnDefinition = "TEXT")
    var state: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "game_user",
        joinColumns = [JoinColumn(name = "game_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val players: MutableSet<UserEntity> = mutableSetOf()

): BaseEntity(id)