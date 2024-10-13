package com.example.jobsearchapp.ui.favorites.presently.list.states

data class FavoriteScreenState (
    val isLoading: Boolean = false,
    val favoriteList: List<FavoriteState> = mutableListOf(),
    val hasError: Boolean = false,
)