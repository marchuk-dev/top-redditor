package com.example.topredditor.data

import com.example.topredditor.model.Post
import com.example.topredditor.network.RedditApi
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: RedditApi,
) : PostRepository {
    override suspend fun getTopPosts(): List<Post> {
        api.getTopPosts()
        return emptyList() // TODO
    }
}