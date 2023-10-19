package me.wangyu.quaerosserver.domain.snippets.handler

import kotlinx.coroutines.reactive.awaitFirstOrNull
import me.wangyu.quaerosserver.domain.snippets.dto.CreateSnippetRequest
import me.wangyu.quaerosserver.domain.snippets.service.command.CommandSnippetService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class SnippetHandler(
    private val commandSnippetService: CommandSnippetService
) {
    suspend fun createSnippet(request: ServerRequest): ServerResponse {
        val snippetRequest = request.bodyToMono<CreateSnippetRequest>().awaitFirstOrNull()
        if (snippetRequest != null) {
            commandSnippetService.createSnippet(snippetRequest)
        } else {
            return ServerResponse.status(HttpStatus.BAD_REQUEST).json().buildAndAwait()
        }
        return ServerResponse.status(HttpStatus.CREATED).json().buildAndAwait()
    }

}