package com.example.jobsearchapp.ui.home.presently.card.states

data class CardScreenStates (
    val isLoading: Boolean = false,
    val card: CardState = CardState(),
    val hasError: Boolean = false,
)