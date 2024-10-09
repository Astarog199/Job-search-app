package com.example.jobsearchapp.ui.home.presently.card

import androidx.lifecycle.ViewModelProvider
import com.example.jobsearchapp.ui.common.ServiceLocator
import com.example.jobsearchapp.ui.home.presently.card.states.CardStateMapper

object FeatureServiceLocator {

    fun provideCardViewModelFactory(itemId: String) : ViewModelProvider.Factory {
        return HomeCardViewModelFactory(
            consumeVacanciesUseCase = ServiceLocator.provideConsumeVacanciesCardUseCase() ,
            cardStateMapper = provideCardStateMapper(),
            vacanciesID = itemId
        )
    }

    private fun provideCardStateMapper() : CardStateMapper {
        return CardStateMapper()
    }
}