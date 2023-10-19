package me.wangyu.quaerosserver.domain.snippets.service.command

import me.wangyu.quaerosserver.domain.snippets.persistence.domain.Snippet
import me.wangyu.quaerosserver.domain.snippets.persistence.dao.SnippetRepository
import me.wangyu.quaerosserver.domain.snippets.dto.CreateSnippetRequest
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