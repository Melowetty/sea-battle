package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "game")
class GameEntity(
    id: Long = 0L,

    @Column(name = "state", columnDefinition = "JSONB")
    var state: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER)
    val players: MutableSet<UserEntity> = mutableSetOf()

): BaseEntity(id)