package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.models.CommonDto
import retrofit2.await

class CommonRemoteDataSource(private val apiService: CommonApiService) {
    suspend fun getDTO(): CommonDto {
        return  apiService.getDto().await()
//        return try {
//            homeApiService.getDto().await()
//        } catch (e: Exception) {
//            println("Error while getting query result from server: $e")
//            CommonDto(offers = emptyList(), vacancies =  emptyList())
//        }
    }
}