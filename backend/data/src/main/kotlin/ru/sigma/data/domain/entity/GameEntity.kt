package ru.sigma.data.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import ru.sigma.data.domain.converter.JsonbConverter
import java.time.Instant

@Entity
@Table(name = "game")
data class GameEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Int? = null,

    @Column(name = "status", nullable = false)
    val status: String,

    @Column(name = "state", columnDefinition = "jsonb")
    @Convert(converter = JsonbConverter::class)
    val state: Map<String, Any>? = null,

    @Column(name = "started_at")
    val startedAt: Instant? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @Column(name = "end_at")
    val endAt: Instant? = null,

    @OneToOne(mappedBy = "game", cascade = [CascadeType.ALL])
    val gameLink: GameLinkEntity? = null,

    @ManyToMany(mappedBy = "games")
    val players: MutableSet<UsersEntity> = mutableSetOf()
)