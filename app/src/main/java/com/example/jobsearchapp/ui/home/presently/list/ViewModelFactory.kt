package com.example.jobsearchapp.ui.home.presently.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesUseCase
import com.example.jobsearchapp.ui.home.domain.ConsumeOffersUseCase

import com.example.jobsearchapp.ui.home.presently.list.states.HomeStateMapper

class ViewModelFactory(
    private val consumeVacanciesUseCase: ConsumeVacanciesUseCase,
    private val consumeOffersUseCase: ConsumeOffersUseCase,
    private val homeStateMapper: HomeStateMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(
                    consumeVacanciesUseCase = consumeVacanciesUseCase,
                    consumeOffersUseCase = consumeOffersUseCase,
                    homeStateMapper = homeStateMapper
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}