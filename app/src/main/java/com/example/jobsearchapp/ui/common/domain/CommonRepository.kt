package com.example.jobsearchapp.ui.common.domain

import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import kotlinx.coroutines.flow.Flow

interface CommonRepository {
    fun consumeVacancies(): Flow<List<CommonDomainEntity>>
    fun consumeFavoriteVacancies(): Flow<List<CommonDomainEntity>>
    suspend fun changeFavoriteState(id: String)
}