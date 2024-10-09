package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.dto.CommonDto
import retrofit2.Call
import retrofit2.http.GET

interface CommonApiService {
    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
   fun getDto() : Call<CommonDto>
}