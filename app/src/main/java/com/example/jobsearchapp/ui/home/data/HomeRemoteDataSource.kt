package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.common.data.dto.CommonDto
import retrofit2.await

class HomeRemoteDataSource (private val apiService: HomeApiService) {

    suspend fun getDTO(): CommonDto {
        return try {
            apiService.getHomeDto().await()
        } catch (e: Exception) {
            println("Error while getting query result from server: $e")
            CommonDto(offers = emptyList(), vacancies =  emptyList())
        }
    }
}