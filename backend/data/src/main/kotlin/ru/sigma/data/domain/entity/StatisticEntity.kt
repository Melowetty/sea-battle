package ru.sigma.data.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable
class StatisticEntity(
    @Column(name = "win", nullable = false)
    var win: Int = 0,

    @Column(name = "loss", nullable = false)
    var loss: Int = 0
)
