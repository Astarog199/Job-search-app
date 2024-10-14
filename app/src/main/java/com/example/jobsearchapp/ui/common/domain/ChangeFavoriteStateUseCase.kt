package com.example.jobsearchapp.ui.common.domain

import com.example.jobsearchapp.ui.common.data.CommonRepository
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import javax.inject.Inject

class ChangeFavoriteStateUseCase @Inject constructor (
    private val repository: CommonRepository
) {
    suspend operator fun invoke(vacancies: CommonDomainEntity){
        repository.changeFavoriteState(vacancies)
    }
}