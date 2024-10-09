package com.example.jobsearchapp.ui.home.presently.list

import androidx.lifecycle.ViewModelProvider
import com.example.jobsearchapp.ui.common.ServiceLocator as commonLocator
import com.example.jobsearchapp.ServiceLocator as serviceLocator
import com.example.jobsearchapp.ui.home.presently.list.states.HomeStateMapper

object FeatureServiceLocator {

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(
            consumeVacanciesUseCase = commonLocator.provideConsumeVacanciesUseCase(),
            consumeOffersUseCase = serviceLocator.provideConsumeOffersUseCase(),
            homeStateMapper = provideHomeStateMapper()
        )
    }

    private fun provideHomeStateMapper(): HomeStateMapper {
        return HomeStateMapper()
    }
}