package com.example.jobsearchapp.ui.home.presently.states

data class HomeScreenState (
    val isLoading: Boolean = false,
    val offersList: List<OffersState> = mutableListOf(),
    val vacanciesList: List<VacanciesState> = mutableListOf(),
    val hasError: Boolean = false,
)