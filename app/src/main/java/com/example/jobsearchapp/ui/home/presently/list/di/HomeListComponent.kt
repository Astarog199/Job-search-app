package com.example.jobsearchapp.ui.home.presently.list.di

import com.example.jobsearchapp.di.FeatureScope
import com.example.jobsearchapp.ui.home.presently.list.HomeFragment
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