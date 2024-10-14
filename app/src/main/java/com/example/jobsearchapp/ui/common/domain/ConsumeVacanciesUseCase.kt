package com.example.jobsearchapp.ui.common.domain

import com.example.jobsearchapp.ui.common.data.CommonRepository
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConsumeVacanciesUseCase @Inject constructor(
    private val repository: CommonRepository
) {
    operator fun invoke() : Flow<List<CommonDomainEntity>> {
        return repository.consumeVacancies()
    }
}