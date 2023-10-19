package me.wangyu.quaerosserver.global.security.auth

import me.wangyu.quaerosserver.global.security.jwt.JwtProvider
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class AuthenticationManager(
    private val jwtProvider: JwtProvider
): ReactiveAuthenticationManager {

    @SuppressWarnings("unchecked")
    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        val token: String = authentication?.credentials.toString()
        val email: String = jwtProvider.getEmailFormToken(token)
        return Mono.just(jwtProvider.validateToken(token))
            .filter  {valid -> valid }
            .switchIfEmpty(Mono.empty())
            .map {
                val claims = jwtProvider.getAllClaimsFromToken(token)
                UsernamePasswordAuthenticationToken(
                    email,
                    null
                )
            }
    }
}