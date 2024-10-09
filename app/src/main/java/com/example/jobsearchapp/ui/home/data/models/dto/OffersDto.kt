package com.example.jobsearchapp.ui.home.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OffersDto (
    @Json(name = "id")  val id: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "button") val button: ButtonDto?,
    @Json(name = "link") val link: String?
)