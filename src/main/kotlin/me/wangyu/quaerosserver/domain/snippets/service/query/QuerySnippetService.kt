package me.wangyu.quaerosserver.domain.snippets.service.query

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.wangyu.quaerosserver.domain.snippets.dto.SnippetResponse
import me.wangyu.quaerosserver.domain.snippets.persistence.dao.SnippetRepository
import org.springframework.stereotype.Service

@Service
class QuerySnippetService (
    private val snippetRepository: SnippetRepository
) {
    suspend fun getSnippets(): Flow<SnippetResponse> {
        return snippetRepository.findAll().map { SnippetResponse.of(it) }
    }
}