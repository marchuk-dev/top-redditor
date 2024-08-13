package com.example.topredditor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topredditor.ui.theme.TopPostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TopPostsViewModel @Inject constructor() : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(TopPostsUiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _uiStateFlow.update {
                TopPostsUiState(
                    posts = List(50) {
                        SAMPLE_POST.copy(id = UUID.randomUUID().toString())
                    }
                )
            }
        }
    }
}