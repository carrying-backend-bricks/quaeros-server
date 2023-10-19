package me.wangyu.quaerosserver.global.security.auth

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Function


@Component
class SecurityContextRepository(
    private val authenticationManager: AuthenticationManager
): ServerSecurityContextRepository {
    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        throw UnsupportedOperationException()
    }

    override fun load(swe: ServerWebExchange?): Mono<SecurityContext> {
        return if (swe != null) {
            Mono.justOrEmpty(swe.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
                .filter { authHeader -> authHeader.startsWith("Bearer ") }
                .flatMap { authHeader ->
                    val authToken = authHeader.substring(7)
                    val auth = UsernamePasswordAuthenticationToken(authToken, authToken)
                    authenticationManager.authenticate(auth).map { SecurityContextImpl(it) }
                }
        } else {
            Mono.empty()
        }
    }
}