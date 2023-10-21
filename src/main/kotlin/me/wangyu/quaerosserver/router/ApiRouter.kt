package me.wangyu.quaerosserver.router

import me.wangyu.quaerosserver.domain.snippets.handler.SnippetHandler
import me.wangyu.quaerosserver.domain.user.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiRouter {
    @Bean
    fun snippetRoute(snippetHandler: SnippetHandler) = coRouter {
       "/snippet".nest {
           POST("/") {snippetHandler.createSnippet(it)}
           GET("/" ) {snippetHandler.getSnippets(it)}
           GET("{id}") {snippetHandler.getSnippetById(it)}
       }
    }

    @Bean
    fun userRoute(userHandler: UserHandler) = coRouter {
        "/user".nest {
            POST("/") {userHandler.createUser(it)}
            GET("/") {userHandler.loginUser(it)}
        }
    }
}