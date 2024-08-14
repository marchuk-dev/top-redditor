package com.example.topredditor.data

import com.example.topredditor.model.ApiResponse
import com.example.topredditor.model.Post

interface PostRepository {
    suspend fun getTopPosts(limit: Int, after: String? = null): ApiResponse
}