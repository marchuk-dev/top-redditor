package com.example.topredditor.data

import com.example.topredditor.model.Post
import com.example.topredditor.network.RedditApi
import com.example.topredditor.network.model.asExternalModel
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: RedditApi,
) : PostRepository {
    override suspend fun getTopPosts(limit: Int): List<Post> =
        api.getTopPosts(limit).asExternalModel()
}