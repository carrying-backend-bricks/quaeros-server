package me.wangyu.quaerosserver.domain.snippet.persistence.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_snippet")
data class Snippet(
    @Id
    @Column("snippet_id")
    val id: String? = null,
    @Column("title")
    val title: String,
    @Column("code")
    val code: String
)
