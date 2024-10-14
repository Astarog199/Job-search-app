package com.example.jobsearchapp.ui.favorites.presently.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearchapp.di.FeatureScope
import com.example.jobsearchapp.ui.common.domain.ChangeFavoriteStateUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeFavoriteVacanciesUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesCardUseCase
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteStateMapper
import javax.inject.Inject

@FeatureScope
class ViewModelFactory @Inject constructor(
    private val consumeFavoriteVacanciesUseCase: ConsumeFavoriteVacanciesUseCase,
    private val consumeVacanciesCardUseCase: ConsumeVacanciesCardUseCase,
    private val changeFavoriteStateUseCase: ChangeFavoriteStateUseCase,
    private val stateMapper: FavoriteStateMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(
                    consumeFavoriteVacanciesUseCase = consumeFavoriteVacanciesUseCase,
                    consumeVacanciesCardUseCase = consumeVacanciesCardUseCase,
                    changeFavoriteStateUseCase = changeFavoriteStateUseCase,
                    stateMapper = stateMapper
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}