package ru.sigma.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.sigma.data.domain.entity.GameLinkEntity
import ru.sigma.data.domain.entity.GameResultEntity

interface GameResultRepository : JpaRepository<GameResultEntity, Long>