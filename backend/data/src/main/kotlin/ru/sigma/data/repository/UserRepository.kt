package ru.sigma.data.repository

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sigma.data.domain.entity.UserEntity

@Repository
interface UserRepository: JpaRepository<UserEntity, UUID>