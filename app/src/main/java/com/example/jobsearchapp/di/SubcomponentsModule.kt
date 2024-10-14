package com.example.jobsearchapp.di

import com.example.jobsearchapp.ui.favorites.presently.di.FavoriteComponent
import com.example.jobsearchapp.ui.home.presently.card.di.HomeCardComponent
import com.example.jobsearchapp.ui.home.presently.list.di.HomeListComponent
import dagger.Module

@Module(subcomponents = [
    FavoriteComponent::class,
    HomeListComponent::class,
    HomeCardComponent::class
])
object SubcomponentsModule