package me.wangyu.quaerosserver.domain.user.service

import kotlinx.coroutines.flow.Flow
import me.wangyu.quaerosserver.domain.user.dto.TokenResponse
import me.wangyu.quaerosserver.domain.user.dto.UserRequest
import me.wangyu.quaerosserver.domain.user.persistence.dao.UserRepository
import me.wangyu.quaerosserver.domain.user.persistence.domain.value.Password
import me.wangyu.quaerosserver.global.security.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class LoginUserService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider
) {
    suspend fun login(request: UserRequest): TokenResponse {
        val user = userRepository.findByEmail(request.email)
        validatePassword(request.password, user.password)
        return TokenResponse(jwtProvider.generateToken(user))
    }

    private fun validatePassword(password: String, expected: Password) {
        if(!expected.match(password)) {
            throw RuntimeException()
        }
    }
}