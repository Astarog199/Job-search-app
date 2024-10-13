package com.example.jobsearchapp.ui.home.presently.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchapp.ui.common.domain.ChangeFavoriteStateUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesUseCase
import com.example.jobsearchapp.ui.home.domain.ConsumeOffersUseCase
import com.example.jobsearchapp.ui.home.presently.list.states.HomeScreenState
import com.example.jobsearchapp.ui.home.presently.list.states.HomeStateMapper
import com.example.jobsearchapp.ui.home.presently.list.states.OffersState
import com.example.jobsearchapp.ui.home.presently.list.states.VacanciesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val consumeVacanciesUseCase: ConsumeVacanciesUseCase,
    private val consumeOffersUseCase: ConsumeOffersUseCase,
    private val changeFavoriteStateUseCase: ChangeFavoriteStateUseCase,
    private val homeStateMapper: HomeStateMapper
) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _items = MutableStateFlow(HomeScreenState())
    val items: StateFlow<HomeScreenState> = _items.asStateFlow()
    private var offersEntities: List<OffersState> = mutableListOf()
    private var vacanciesEntities: List<VacanciesState> = mutableListOf()

    fun loadItems() {
        combine(
            consumeOffersUseCase(),
            consumeVacanciesUseCase()
        ) { offers, vacancies ->
            offersEntities = offers.map(homeStateMapper::toOffersHomeStateEntity)
            vacanciesEntities = vacancies.map(homeStateMapper::toHomeStateEntity)
        }
            .filter { offersEntities.isNotEmpty() || vacanciesEntities.isNotEmpty() }
            .onStart {
                _items.update { list -> list.copy(isLoading = true) }
            }
            .onEach {
                _items.update { state ->
                    state.copy(isLoading = false, offersList = offersEntities, vacanciesList = vacanciesEntities)
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

    fun changeFavoriteState(vacancies: VacanciesState) {
        scope.launch {
            val commonDomainEntity =
                homeStateMapper.toCommonDomainEntity(vacancies.copy(isFavorite = !vacancies.isFavorite))
            changeFavoriteStateUseCase(commonDomainEntity)
        }
    }
}