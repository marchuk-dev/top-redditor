package com.example.topredditor.model

data class Post(
    val id: String,
    val author: String,
    val title: String,
    val url: String,
    val creationTimeSecondsUtc: Long,
    val thumbnailLink: String?,
    val previewLink: String?,
    val commentsCount: Int,
    val upsCount: Int,
)
