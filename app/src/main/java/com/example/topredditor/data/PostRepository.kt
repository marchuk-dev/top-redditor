package com.example.topredditor.data

import com.example.topredditor.model.ApiResponse

interface PostRepository {
    suspend fun getTopPosts(limit: Int, after: String? = null): ApiResponse
}