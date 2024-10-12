package com.example.jobsearchapp.ui.home.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressDto(
    @Json(name = "town") val town: String,
    @Json(name = "street") val street: String,
    @Json(name = "house") val house: String
)