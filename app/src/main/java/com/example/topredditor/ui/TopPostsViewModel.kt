package com.example.topredditor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topredditor.data.PostRepository
import com.example.topredditor.ui.theme.TopPostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopPostsViewModel
    @Inject
    constructor(
        postRepository: PostRepository,
    ) : ViewModel() {
        private val _uiStateFlow = MutableStateFlow(TopPostsUiState())
        val uiStateFlow = _uiStateFlow.asStateFlow()

        init {
            viewModelScope.launch {
                _uiStateFlow.update {
                    TopPostsUiState(
                        posts = postRepository.getTopPosts(),
                    )
                }
            }
        }
    }
