package com.example.jobsearchapp.ui.home.presently.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesCardUseCase
import com.example.jobsearchapp.ui.home.presently.card.states.CardStateMapper

class HomeCardViewModelFactory (
    private val consumeVacanciesUseCase: ConsumeVacanciesCardUseCase,
    private val cardStateMapper: CardStateMapper,
    private val vacanciesID: String
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(HomeCardViewModel::class.java) -> {
                @Suppress("UNCHEKED_CAST")
                return HomeCardViewModel(
                    consumeVacanciesCardUseCase = consumeVacanciesUseCase,
                    cardStateMapper = cardStateMapper,
                    vacanciesID = vacanciesID
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}