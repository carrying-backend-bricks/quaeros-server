package me.wangyu.quaerosserver.global.converter

import me.wangyu.quaerosserver.domain.user.persistence.domain.User
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.r2dbc.mapping.OutboundRow
import org.springframework.data.relational.core.sql.SqlIdentifier
import org.springframework.stereotype.Component
import org.springframework.r2dbc.core.Parameter;

@Component
@WritingConverter
class UserWriteConverter: Converter<User, OutboundRow> {
    override fun convert(source: User): OutboundRow? {
        val row = OutboundRow()

        val map = mapOf(
            "email" to Parameter.fromOrEmpty(source.email, String::class.java),
            "password" to Parameter.fromOrEmpty(source.password.value, String::class.java)
        )

        map.forEach { (key, value) -> row[SqlIdentifier.quoted(key)] = value }

        return row
    }
}