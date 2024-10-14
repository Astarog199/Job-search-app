package com.example.jobsearchapp.ui.home.presently.card.di

import com.example.jobsearchapp.di.FeatureScope
import com.example.jobsearchapp.ui.home.presently.card.HomeCardFragment
import dagger.Subcomponent
import javax.inject.Named

@FeatureScope
@Subcomponent
interface HomeCardComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create():HomeCardComponent
    }
    fun inject(fragment: HomeCardFragment)
}