package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "game_link")
class GameLinkEntity(
    @Id
    @Column(name = "code")
    val code: String,

    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    val game: GameEntity
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameLinkEntity) return false
        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}