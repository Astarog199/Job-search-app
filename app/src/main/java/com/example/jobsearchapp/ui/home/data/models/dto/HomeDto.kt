package com.example.jobsearchapp.ui.home.data.models.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeDto (
    @Json(name = "offers")  val offers: List<OffersDto>,
    @Json(name ="vacancies") val vacancies: List <VacanciesDto>
)