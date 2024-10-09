package com.example.jobsearchapp.ui.common.domain

import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import kotlinx.coroutines.flow.Flow

class ConsumeVacanciesUseCase(
    private val repository: CommonRepository
) {
    operator fun invoke() : Flow<List<CommonDomainEntity>> {
        return repository.consumeVacancies()
    }
}