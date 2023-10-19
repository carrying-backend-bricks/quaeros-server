package me.wangyu.quaerosserver.domain.snippets.dto

import me.wangyu.quaerosserver.domain.snippets.persistence.domain.Snippet

data class SnippetResponse(
    val id: String?,
    val title: String,
    val code: String
) {
    companion object {
        fun of(snippet: Snippet): SnippetResponse {
            return SnippetResponse(
                id = snippet.id,
                title = snippet.title,
                code = snippet.code
            )
        }
    }
}