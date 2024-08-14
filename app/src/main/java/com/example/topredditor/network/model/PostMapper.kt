package com.example.topredditor.network.model

import com.example.topredditor.model.ApiResponse
import com.example.topredditor.model.Post

fun PostDto.asExternalModel() = ApiResponse.Success(
    after = data.after,
    posts = data.children.map {
        val post = it.data
        val result = Post(
        id = post.id,
        author = post.author,
        title = post.title,
        creationTimeSecondsUtc = post.creationTimeSecondsUtc.toLong(),
        thumbnailLink = post.thumbnailLink,
        previewLink = post.preview?.images?.firstOrNull()?.source?.url,
        commentsCount = post.commentsCount,
    )
    println("PostMapper post: $result, link: ${post.url}, upsCount: ${post.upsCount}")
    result
    }
)


