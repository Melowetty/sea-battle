package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "revoked_token")
class RevokedTokenEntity(
    @Id
    val token: String,

    @Column(nullable = false)
    val expired: LocalDate
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RevokedTokenEntity

        return token == other.token
    }

    override fun hashCode(): Int {
        return token.hashCode()
    }

    override fun toString(): String {
        return "RevokedTokenEntity(token='$token', expired=$expired)"
    }
}