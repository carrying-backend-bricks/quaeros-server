package me.wangyu.quaerosserver.domain.user.persistence.domain.value

import me.wangyu.quaerosserver.global.util.PasswordUtil
import org.springframework.data.relational.core.mapping.Column

data class Password(
    @Column("password")
    val value: String
) {
    fun match(password: String): Boolean {
        return PasswordUtil.matches(value, password)
    }
}