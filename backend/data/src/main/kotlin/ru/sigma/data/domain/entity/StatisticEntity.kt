package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "statistic")
data class StatisticEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Int? = null,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UsersEntity,

    @Column(name = "win", nullable = false)
    var win: Int = 0,

    @Column(name = "loss", nullable = false)
    var loss: Int = 0
)
