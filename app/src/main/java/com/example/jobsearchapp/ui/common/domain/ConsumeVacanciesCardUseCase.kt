package com.example.jobsearchapp.ui.common.domain

import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConsumeVacanciesCardUseCase(
    private val repository: CommonRepository
) {
    operator fun invoke(productId: String): Flow<CommonDomainEntity> {
        return repository.consumeVacancies().map { item ->
            item.first { it.id == productId }
        }
    }
}