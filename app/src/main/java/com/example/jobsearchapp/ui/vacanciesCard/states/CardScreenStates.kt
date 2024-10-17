package com.example.jobsearchapp.ui.vacanciesCard.states

data class CardScreenStates (
    val isLoading: Boolean = false,
    val card: CardState = CardState(),
    val hasError: Boolean = false,
)