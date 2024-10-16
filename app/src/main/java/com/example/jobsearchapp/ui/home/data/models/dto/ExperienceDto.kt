package com.example.jobsearchapp.ui.home.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExperienceDto(
    @Json(name = "previewText") val previewText: String,
    @Json(name = "text") val text: String
)