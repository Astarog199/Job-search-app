package com.example.jobsearchapp.ui.common.domain

import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity

class ChangeFavoriteStateUseCase (
    private val repository: CommonRepository
) {
    suspend operator fun invoke(vacancies: CommonDomainEntity){
        repository.changeFavoriteState(vacancies)
    }
}