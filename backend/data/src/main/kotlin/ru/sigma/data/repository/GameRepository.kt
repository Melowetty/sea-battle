package ru.sigma.data.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sigma.data.domain.entity.GameEntity

@Repository
interface GameRepository : JpaRepository<GameEntity, Long>