package ru.sigma.security

import ru.sigma.common.model.UserInfo
import ru.sigma.data.domain.entity.UserEntity

object Extensions {
    fun UserEntity.toUserInfo() = UserInfo(
        id = id,
        telegramId = telegramId,
    )
}