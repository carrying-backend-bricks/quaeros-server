package me.wangyu.quaerosserver.global.security.auth

import me.wangyu.quaerosserver.global.security.jwt.JwtProvider
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class AuthenticationManager(
    private val jwtProvider: JwtProvider
): ReactiveAuthenticationManager {

    @SuppressWarnings("unchecked")
    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        val token: String = authentication?.credentials.toString()
        val id: String = jwtProvider.getIdFormToken(token)
        val validatedMono = Mono.just(jwtProvider.validateToken(token))
            .filter { valid -> valid }
            .switchIfEmpty(Mono.empty())

        return validatedMono.flatMap {
            Mono.just(UsernamePasswordAuthenticationToken(id, null, emptyList<GrantedAuthority>()))
        }
    }
}