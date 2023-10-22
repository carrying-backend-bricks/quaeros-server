package me.wangyu.quaerosserver.domain.user.service

import me.wangyu.quaerosserver.domain.user.dto.request.UserRequest
import me.wangyu.quaerosserver.domain.user.persistence.dao.UserRepository
import me.wangyu.quaerosserver.domain.user.persistence.domain.User
import me.wangyu.quaerosserver.domain.user.persistence.domain.value.Password
import me.wangyu.quaerosserver.global.util.PasswordUtil
import org.springframework.stereotype.Service

@Service
class CommandUserService(
    private val userRepository: UserRepository,
    private val passwordUtil: PasswordUtil
) {
    suspend fun createUser(request: UserRequest) {
        print("service")
        userRepository.save(
            User(email = request.email, password = Password(passwordUtil.encode(request.password)))
        )
    }
}