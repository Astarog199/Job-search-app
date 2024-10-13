package com.example.jobsearchapp.ui.favorites.presently

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchapp.ui.common.domain.ChangeFavoriteStateUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeFavoriteVacanciesUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesCardUseCase
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteScreenState
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteState
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteStateMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val consumeFavoriteVacanciesUseCase: ConsumeFavoriteVacanciesUseCase,
    private val consumeVacanciesCardUseCase: ConsumeVacanciesCardUseCase,
    private val changeFavoriteStateUseCase: ChangeFavoriteStateUseCase,
    private val stateMapper: FavoriteStateMapper
) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _items = MutableStateFlow(FavoriteScreenState())
    val items: StateFlow<FavoriteScreenState> = _items.asStateFlow()

    fun loadItems() {
        consumeFavoriteVacanciesUseCase()
            .filter { it.isNotEmpty() }
            .map { vacancies->
                vacancies.map(stateMapper::toFavoriteState)
            }
            .onStart {
                _items.update { list -> list.copy(isLoading = true) }
            }
            .onEach { vacancies ->
                _items.update { state ->
                    state.copy(isLoading = false, favoriteList = vacancies)
                }
            }
            .catch {
                _items.update { screenState ->
                    screenState.copy(hasError = true)
                }
            }
            .launchIn(viewModelScope)
    }

    fun errorShown() {
        _items.update { screenState -> screenState.copy(hasError = false) }
    }

    fun changeFavoriteState(vacancies: FavoriteState) {
        scope.launch {
//            val entity = stateMapper.toFavoriteState(vacancies.copy(isFavorite = !vacancies.isFavorite))
//            changeFavoriteStateUseCase(entity)
        }
    }
}