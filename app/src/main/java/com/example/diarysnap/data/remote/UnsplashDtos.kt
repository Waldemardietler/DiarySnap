package com.example.diarysnap.data.remote

import com.squareup.moshi.Json

data class UnsplashSearchResponse(
    val results: List<UnsplashPhotoDto>
)

data class UnsplashPhotoDto(
    val id: String,
    val urls: UnsplashUrlsDto
)

data class UnsplashUrlsDto(
    @Json(name = "regular") val regular: String
)

