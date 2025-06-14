package ru.sigma.data.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant
import org.hibernate.annotations.CreationTimestamp
import ru.sigma.data.domain.model.game.GameState

@Entity
@Table(name = "game")
class GameEntity(
    id: Long = 0L,

    @Column(name = "state", columnDefinition = "JSONB")
    var state: GameState,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER)
    val players: MutableSet<UserEntity> = mutableSetOf()

): BaseEntity(id)