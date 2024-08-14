package com.example.topredditor.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PostDto(
    val data: PostData
)

@Serializable
data class PostData(
    val after: String,
    val children: List<PostWrapper>
)

@Serializable
data class PostWrapper(
    val data: RedditPostDto
)

@Serializable
data class RedditPostDto(
    @SerialName("name") val id: String,
    val author: String,
    val title: String,
    @SerialName("created_utc") val creationTimeSecondsUtc: Double,
    @SerialName("thumbnail") val thumbnailLink: String?,
    val preview: Preview? = null,
    @SerialName("num_comments") val commentsCount: Int,
    @SerialName("permalink") val url: String,
    @SerialName("ups") val upsCount: Int,
)

@Serializable
data class Preview(
    val images: List<PreviewImage>?
)

@Serializable
data class PreviewImage(
    val source: PreviewImageSource
)

@Serializable
data class PreviewImageSource(
    val url: String
)