package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.dto.HomeDto
import retrofit2.await
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeApiService) {

    suspend fun getDTO(): HomeDto {
        return try {
            apiService.getHomeDto().await()
        } catch (e: Exception) {
            println("Error while getting query result from server: $e")
            HomeDto(offers = emptyList(), vacancies =  emptyList())
        }
    }
}