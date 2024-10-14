package com.example.jobsearchapp.ui.home.domain


import com.example.jobsearchapp.ui.home.data.HomeRepository
import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConsumeOffersUseCase @Inject constructor (private val homeRepository: HomeRepository) {
    operator fun invoke() : Flow<List<OffersDomainEntity>> {
        return homeRepository.consumeOffers()
    }
}