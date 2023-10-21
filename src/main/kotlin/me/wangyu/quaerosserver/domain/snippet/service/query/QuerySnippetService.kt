package me.wangyu.quaerosserver.domain.snippet.service.query

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.wangyu.quaerosserver.domain.snippet.dto.SnippetResponse
import me.wangyu.quaerosserver.domain.snippet.persistence.dao.SnippetRepository
import org.springframework.stereotype.Service

@Service
class QuerySnippetService (
    private val snippetRepository: SnippetRepository
) {
    suspend fun getSnippets(): Flow<SnippetResponse> {
        return snippetRepository.findAll().map { SnippetResponse.of(it) }
    }
    suspend fun getSnippetById(snippetId: Long): SnippetResponse{
        return SnippetResponse.of(snippetRepository.findById(snippetId))
    }
}