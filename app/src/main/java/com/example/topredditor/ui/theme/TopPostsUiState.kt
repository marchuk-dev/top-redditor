package com.example.topredditor.ui.theme

import com.example.topredditor.model.Post

data class TopPostsUiState(
    val isLoading: Boolean = true,
    val posts: List<Post> = emptyList(),
)