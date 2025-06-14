package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "game_result")
class GameResultEntity (
    id: Long,

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = false)
    val winner: UserEntity,

    @Column(name = "started_at", nullable = false)
    val startedAt: Instant,

    @Column(name = "end_at", nullable = false)
    val endAt: Instant,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "game_result_user",
        joinColumns = [JoinColumn(name = "game_result_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val players: MutableSet<UserEntity> = mutableSetOf()
): BaseEntity(id)