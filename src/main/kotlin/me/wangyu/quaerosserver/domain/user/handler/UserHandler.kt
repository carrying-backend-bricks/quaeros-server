package me.wangyu.quaerosserver.domain.user.handler

import kotlinx.coroutines.reactive.awaitFirstOrNull
import me.wangyu.quaerosserver.domain.user.dto.UserRequest
import me.wangyu.quaerosserver.domain.user.service.CommandUserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class UserHandler(
    private val commandUserService: CommandUserService
) {
    suspend fun createUser(request: ServerRequest): ServerResponse {
        val userRequest = request.bodyToMono<UserRequest>().awaitFirstOrNull()
        if (userRequest != null) {
            commandUserService.createUser(userRequest)
        } else {
            return ServerResponse.status(HttpStatus.BAD_REQUEST).json().buildAndAwait()
        }
        return ServerResponse.status(HttpStatus.CREATED).json().buildAndAwait()
    }

}