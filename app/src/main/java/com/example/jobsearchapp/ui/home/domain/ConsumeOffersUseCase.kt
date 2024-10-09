package com.example.jobsearchapp.ui.home.domain


import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity
import kotlinx.coroutines.flow.Flow

class ConsumeOffersUseCase (private val homeRepository: HomeRepository) {
    operator fun invoke() : Flow<List<OffersDomainEntity>> {
        return homeRepository.consumeOffers()
    }
}