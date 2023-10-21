package me.wangyu.quaerosserver.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import me.wangyu.quaerosserver.domain.user.persistence.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct

@Component
class JwtProvider {
    @Value("\${quaeros.jwt.secretKey}")
    private val secretKey: String? = null
    private var key: Key? = null

    @PostConstruct
    fun init() {
        this.key = Keys.hmacShaKeyFor(secretKey?.toByteArray());
    }

    fun generateToken(user: User): String {
        val now: Instant = Instant.now()
        return Jwts.builder()
            .setSubject(user.id)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(Duration.ofDays(3))))
            .signWith(key)
            .compact()
    }

    fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    fun getIdFormToken(token: String): String {
        return getAllClaimsFromToken(token).subject
    }

    fun validateToken(token: String): Boolean {
        val expiration: Date = getAllClaimsFromToken(token).expiration
        return !expiration.before(Date())
    }
}