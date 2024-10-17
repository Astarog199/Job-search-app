package com.example.jobsearchapp.di

import android.content.Context
import com.example.jobsearchapp.ui.favorites.presently.di.FavoriteComponent
import com.example.jobsearchapp.ui.vacanciesCard.di.HomeCardComponent
import com.example.jobsearchapp.ui.home.presently.HomeListComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    DataModule::class,
    SubcomponentsModule::class
])

interface AppComponent {
    fun favoritesFragmentFactory(): FavoriteComponent.Factory
    fun homeListFragmentFactory(): HomeListComponent.Factory
    fun homeCardFragmentFactory(): HomeCardComponent.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}