package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.OffersDataEntity
import com.example.jobsearchapp.ui.home.data.models.dto.HomeDto
import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
    private val dataMapper: HomeDataMapper,
    private val domainMapper: HomeDomainMapper,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    fun consumeOffers(): Flow<List<OffersDomainEntity>> {
        return  saveOffers().map { list ->
            list.map(domainMapper::toOffersDomainEntity)
        }
    }

    private  fun saveOffers() : Flow<List<OffersDataEntity>> {
            scope.launch {
            val dto = homeRemoteDataSource.getDTO()
            localDataSource.save(
                dto.offers.map(dataMapper::toOffersDataEntity)
            )
        }
        return localDataSource.consume().flowOn(coroutineDispatcher)
    }
}