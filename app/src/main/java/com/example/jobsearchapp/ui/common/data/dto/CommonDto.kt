package com.example.jobsearchapp.ui.common.data.dto

import com.example.jobsearchapp.ui.home.data.models.dto.OffersDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonDto (
    @Json(name = "offers")  val offers: List<OffersDto>,
    @Json(name ="vacancies") val vacancies: List <VacanciesDto>
)