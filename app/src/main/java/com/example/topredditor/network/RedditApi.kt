package com.example.topredditor.network

import com.example.topredditor.network.model.PostDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("top.json")
    suspend fun getTopPosts(
        @Query("limit") limit: Int,
        @Query("after") after: String?,
    ): PostDto
}