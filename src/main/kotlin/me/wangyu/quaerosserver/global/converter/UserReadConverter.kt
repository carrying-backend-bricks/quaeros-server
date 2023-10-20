package me.wangyu.quaerosserver.global.converter

import io.r2dbc.spi.Row
import me.wangyu.quaerosserver.domain.user.persistence.domain.User
import me.wangyu.quaerosserver.domain.user.persistence.domain.value.Password
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.stereotype.Component

@Component
@ReadingConverter
class UserReadConverter: Converter<Row, User> {
    override fun convert(source: Row): User? {
        return User(
            id = source.get("user_id") as String,
            email = source.get("email") as String,
            password = Password(
                value = source.get("password") as String
            )
        )
    }
}