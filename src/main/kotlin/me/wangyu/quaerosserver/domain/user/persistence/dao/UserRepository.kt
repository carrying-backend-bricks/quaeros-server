package me.wangyu.quaerosserver.domain.user.persistence.dao

import me.wangyu.quaerosserver.domain.user.persistence.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CoroutineCrudRepository<User, Long>{
}