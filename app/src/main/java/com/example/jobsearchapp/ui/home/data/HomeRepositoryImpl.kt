package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.dto.HomeDto
import com.example.jobsearchapp.ui.home.domain.HomeRepository
import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
    private val dataMapper: HomeDataMapper,
    private val domainMapper: HomeDomainMapper,
    private val coroutineDispatcher: CoroutineDispatcher,
) : HomeRepository {
    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)
    private var dto = HomeDto(offers = emptyList(), vacancies = emptyList())

    init {
        scope.launch {
            getApiService()
            saveVacancies()
        }
    }

    override fun consumeOffers(): Flow<List<OffersDomainEntity>> {
        return localDataSource.consume().map { list ->
            list.map(domainMapper::toOffersDomainEntity)
        }
    }

    private suspend fun getApiService() {
        dto = homeRemoteDataSource.getDTO()
    }

    private suspend fun saveVacancies() {
        localDataSource.save(dto.offers.map(dataMapper::toOffersDataEntity))
    }
}