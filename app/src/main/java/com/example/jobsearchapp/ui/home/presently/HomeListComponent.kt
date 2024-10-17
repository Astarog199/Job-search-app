package com.example.jobsearchapp.ui.home.presently

import com.example.jobsearchapp.di.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent
interface HomeListComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): HomeListComponent
    }
    fun inject(fragment: HomeFragment)
}