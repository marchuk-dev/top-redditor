package com.example.topredditor.model

sealed interface ApiResponse {
    data class Error(
        val exception: Exception,
    ) : ApiResponse

    data class Success(
        val after: String,
        val posts: List<Post>,
    ) : ApiResponse
}
