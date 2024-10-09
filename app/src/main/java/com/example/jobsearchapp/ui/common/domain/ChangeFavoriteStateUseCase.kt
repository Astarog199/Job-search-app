package com.example.jobsearchapp.ui.common.domain

class ChangeFavoriteStateUseCase (
    private val repository: CommonRepository
) {
    suspend operator fun invoke(id: String){
        repository.changeFavoriteState(id)
    }
}