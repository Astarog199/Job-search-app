package com.example.jobsearchapp.ui.favorites.presently.di

import com.example.jobsearchapp.di.FeatureScope
import com.example.jobsearchapp.ui.favorites.presently.list.FavoritesFragment
import dagger.Subcomponent

@FeatureScope
@Subcomponent
interface FavoriteComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): FavoriteComponent
    }
    fun inject(fragment: FavoritesFragment)
}