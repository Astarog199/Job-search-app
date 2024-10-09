package com.example.jobsearchapp.ui.home.domain

import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun consumeOffers(): Flow<List<OffersDomainEntity>>
}