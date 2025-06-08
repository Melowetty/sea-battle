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
import ru.sigma.data.domain.model.GameStatus

@Entity
@Table(name = "game")
class GameEntity(
    id: Long = 0L,

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    val status: GameStatus,

    @Column(name = "state", columnDefinition = "JSONB")
    val state: Any? = null,

    @Column(name = "started_at")
    val startedAt: Instant? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @Column(name = "end_at")
    val endAt: Instant? = null,

    @OneToOne(mappedBy = "game", cascade = [CascadeType.ALL])
    val gameLink: GameLinkEntity? = null,

    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER)
    val players: MutableSet<UserEntity> = mutableSetOf()
): BaseEntity(id)