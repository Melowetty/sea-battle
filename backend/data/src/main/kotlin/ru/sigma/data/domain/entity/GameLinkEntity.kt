package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "game_link")
data class GameLinkEntity(
    @Id
    @Column(name = "code", nullable = false, unique = true)
    val code: String,

    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    val game: GameEntity
)