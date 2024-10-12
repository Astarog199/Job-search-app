package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.dto.HomeDto
import retrofit2.Call
import retrofit2.http.GET

interface HomeApiService {
    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    fun getHomeDto() : Call<HomeDto>
}