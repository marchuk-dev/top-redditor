package com.example.topredditor.data

import com.example.topredditor.model.Post

interface PostRepository {
    suspend fun getTopPosts(): List<Post>
}