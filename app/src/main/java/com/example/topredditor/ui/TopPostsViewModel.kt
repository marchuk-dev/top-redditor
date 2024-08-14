package com.example.topredditor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topredditor.data.PostRepository
import com.example.topredditor.model.ApiResponse
import com.example.topredditor.model.Post
import com.example.topredditor.ui.theme.TopPostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val POSTS_LIMIT = 10

@HiltViewModel
class TopPostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(TopPostsUiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _uiStateFlow.collect {
                println("TopPostsViewModel: uiStateFlow: $it")
            }
        }
        viewModelScope.launch {
            val apiResponse = postRepository.getTopPosts(limit = POSTS_LIMIT)
            val posts: List<Post>
            val after: String
            if (apiResponse is ApiResponse.Success) {
                posts = apiResponse.posts
                after = apiResponse.after
            } else {
                posts = emptyList()
                after = ""
            }

            _uiStateFlow.update {
                TopPostsUiState(
                    posts = posts,
                    isLoading = false,
                    isError = apiResponse is ApiResponse.Error,
                    after = after,
                )
            }
        }
    }

    fun loadMore() = viewModelScope.launch {
        _uiStateFlow.update { it.copy(isLoading = true) }
        val newApiResponse = postRepository.getTopPosts(
            limit = POSTS_LIMIT,
            after = _uiStateFlow.value.after,
        )

        _uiStateFlow.update { currentList ->
            TopPostsUiState(
                isLoading = false,
                posts = currentList.posts + if (newApiResponse is ApiResponse.Success) {
                    newApiResponse.posts
                } else {
                    emptyList()
                },
                after = if (newApiResponse is ApiResponse.Success) newApiResponse.after else "",
            )
        }
    }
}
