package me.wangyu.quaerosserver.domain.snippet.service.command

import me.wangyu.quaerosserver.domain.snippet.persistence.domain.Snippet
import me.wangyu.quaerosserver.domain.snippet.persistence.dao.SnippetRepository
import me.wangyu.quaerosserver.domain.snippet.dto.request.CreateSnippetRequest
import org.springframework.stereotype.Service

@Service
class CommandSnippetService(
    private val snippetRepository: SnippetRepository
) {
    suspend fun createSnippet(request: CreateSnippetRequest){
        val snippet = Snippet(title = request.title, code = request.code)
        snippetRepository.save(snippet)
    }
}