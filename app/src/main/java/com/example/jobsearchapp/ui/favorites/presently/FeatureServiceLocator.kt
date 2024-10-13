package com.example.jobsearchapp.ui.favorites.presently

import androidx.lifecycle.ViewModelProvider
import com.example.jobsearchapp.ui.common.ServiceLocator as commonLocator
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteStateMapper

object FeatureServiceLocator {
    fun provideViewModelFactory() : ViewModelProvider.Factory {
        return ViewModelFactory(
            consumeFavoriteVacanciesUseCase = commonLocator.provideConsumeFavoriteVacanciesUseCase(),
            consumeVacanciesCardUseCase = commonLocator.provideConsumeVacanciesCardUseCase(),
            changeFavoriteStateUseCase = commonLocator.provideChangeFavoriteStateUseCase(),
            stateMapper = provideFavoriteStateMapper()
        )
    }

    private fun provideFavoriteStateMapper() : FavoriteStateMapper {
        return FavoriteStateMapper()
    }
}