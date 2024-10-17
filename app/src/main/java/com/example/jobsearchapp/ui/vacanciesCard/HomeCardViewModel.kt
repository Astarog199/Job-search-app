package com.example.jobsearchapp.ui.vacanciesCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesCardUseCase
import com.example.jobsearchapp.ui.vacanciesCard.states.CardScreenStates
import com.example.jobsearchapp.ui.vacanciesCard.states.CardStateMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeCardViewModel(
    private val consumeVacanciesCardUseCase: ConsumeVacanciesCardUseCase,
    private val cardStateMapper: CardStateMapper,
) : ViewModel() {
    private val _state = MutableStateFlow(CardScreenStates())
    val state: StateFlow<CardScreenStates> = _state.asStateFlow()

    fun loadCard(vacanciesID: String) {
        consumeVacanciesCardUseCase(vacanciesID)
            .map { card ->
                cardStateMapper.toHomeCardState(card)
            }
            .onStart {
                _state.update { cardScreenStates -> cardScreenStates.copy(isLoading = true) }
            }

            .onEach { item ->
                println(vacanciesID)
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        card = item
                    )
                }
            }
            .catch {
                cheduleRefresh(vacanciesID)

                _state.update { cardScreenStates ->
                    cardScreenStates.copy(hasError = true)
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun cheduleRefresh(vacanciesID: String) {
        viewModelScope.launch {
            delay(5000)
            clearError()
            loadCard(vacanciesID)
        }
    }

    fun clearError() {
        _state.update { screenState -> screenState.copy(hasError = false) }
    }
}