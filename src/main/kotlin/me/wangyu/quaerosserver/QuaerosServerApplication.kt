package me.wangyu.quaerosserver

import me.wangyu.quaerosserver.global.properties.DatasourceProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DatasourceProperties::class)
class QuaerosServerApplication

fun main(args: Array<String>) {
    runApplication<QuaerosServerApplication>(*args)
}