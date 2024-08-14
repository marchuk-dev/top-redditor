package com.example.topredditor.ui.theme

import com.example.topredditor.model.ApiResponse
import com.example.topredditor.model.Post

data class TopPostsUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val after: String = "",
)