package me.wangyu.quaerosserver.domain.user.persistence.dao

import me.wangyu.quaerosserver.domain.user.persistence.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: CoroutineCrudRepository<User, String>{
    suspend fun findByEmail(email: String): User
}