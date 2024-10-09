package com.example.jobsearchapp.ui.home.presently.list.states

data class HomeScreenState (
    val isLoading: Boolean = false,
    val vacanciesList: List<HomeState> = mutableListOf(),
    val hasError: Boolean = false,
)