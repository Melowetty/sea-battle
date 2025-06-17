package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "room")
class RoomEntity(
    @Id
    @Column(name = "code")
    val code: String,

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    val host: UserEntity,

    @ManyToMany
    @JoinTable(
        name = "room_user",
        joinColumns = [JoinColumn(name = "room_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val players: MutableList<UserEntity> = mutableListOf(),

    @Column(name = "max_size", nullable = false)
    val maxSize: Int,

    var isPublic: Boolean = false,

    val createdAt: Instant = Instant.now()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoomEntity) return false
        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}