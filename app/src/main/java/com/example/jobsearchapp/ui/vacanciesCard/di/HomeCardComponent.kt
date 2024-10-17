package com.example.jobsearchapp.ui.vacanciesCard.di

import com.example.jobsearchapp.di.FeatureScope
import com.example.jobsearchapp.ui.vacanciesCard.HomeCardFragment
import dagger.Subcomponent

@FeatureScope
@Subcomponent
interface HomeCardComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): HomeCardComponent
    }
    fun inject(fragment: HomeCardFragment)
}