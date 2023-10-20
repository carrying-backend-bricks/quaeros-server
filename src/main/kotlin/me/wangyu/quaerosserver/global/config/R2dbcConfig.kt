package me.wangyu.quaerosserver.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.spi.ConnectionFactory
import me.wangyu.quaerosserver.global.converter.UserReadConverter
import me.wangyu.quaerosserver.global.converter.UserWriteConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.DialectResolver

@Configuration
@EnableR2dbcAuditing
class R2dbcConfig {
    @Bean
    fun r2dbcCustomConversions(
        connectionFactory: ConnectionFactory?,
        objectMapper: ObjectMapper?
    ): R2dbcCustomConversions? {
        val dialect = DialectResolver.getDialect(connectionFactory!!)
        val converters = listOf<Any?>(
            UserReadConverter(),
            UserWriteConverter()
        )
        return R2dbcCustomConversions.of(dialect, converters)
    }

}