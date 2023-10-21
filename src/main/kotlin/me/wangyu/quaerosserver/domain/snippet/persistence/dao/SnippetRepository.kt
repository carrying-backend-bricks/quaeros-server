package me.wangyu.quaerosserver.domain.snippet.persistence.dao

import me.wangyu.quaerosserver.domain.snippet.persistence.domain.Snippet
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SnippetRepository: CoroutineCrudRepository<Snippet, Long> {
}