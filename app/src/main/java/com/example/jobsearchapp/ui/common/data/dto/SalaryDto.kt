package com.example.jobsearchapp.ui.common.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SalaryDto(
    @Json(name = "full") val full: String,
)