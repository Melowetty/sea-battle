package ru.sigma.common.context

import ru.sigma.common.model.UserAuthContext

object UserAuthContextHolder {
    private var context: ThreadLocal<UserAuthContext> = ThreadLocal()

    fun get(): UserAuthContext = context.get()
    fun set(userAuth: UserAuthContext) = context.set(userAuth)
}