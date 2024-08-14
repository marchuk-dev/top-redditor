package com.example.topredditor.data

import com.example.topredditor.model.ApiResponse
import com.example.topredditor.network.RedditApi
import com.example.topredditor.network.model.asExternalModel
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: RedditApi,
) : PostRepository {
    override suspend fun getTopPosts(limit: Int, after: String?): ApiResponse = try {
        api.getTopPosts(limit, after).asExternalModel()
    } catch (e: Exception) {
        ApiResponse.Error(e)
    }
}