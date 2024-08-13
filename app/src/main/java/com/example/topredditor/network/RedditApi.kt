package com.example.topredditor.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("top.json")
    suspend fun getTopPosts(
        @Query("limit") limit:Int = 50,
    ): ResponseBody
}