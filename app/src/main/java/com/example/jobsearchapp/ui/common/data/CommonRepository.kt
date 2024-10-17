package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.room.VacanciesEntity
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommonRepository @Inject constructor(
    private val remoteDataSource: CommonRemoteDataSource,
    private val commonDataMapper: CommonDataMapper,
    private val commonDomainMapper: CommonDomainMapper,
    private val commonLocalDataSource: CommonLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    fun consumeVacancies(): Flow<List<CommonDomainEntity>> {
        return saveVacancies()
            .map {
                it.map(commonDomainMapper::toCommonDomainEntity)
            }
    }

     fun consumeFavoriteVacancies(): Flow<List<CommonDomainEntity>> {
        return commonLocalDataSource.getVacanciesEntity()
            .map {
                it.filter { favorites ->
                    favorites.isFavorite
                }.map(commonDomainMapper::toCommonDomainEntity)
            }
    }

    suspend fun changeFavoriteState(vacancies: CommonDomainEntity) {
        val entity = commonDomainMapper.toVacanciesEntity(vacancies)
        commonLocalDataSource.changeFavoriteState(entity)
    }

    private fun saveVacancies() : Flow<List<VacanciesEntity>> {
        scope.launch {
            val vacancies = remoteDataSource.getDTO()
            commonLocalDataSource.save(
                vacancies.vacancies.map(commonDataMapper::toVacanciesEntity)
            )
        }

        return commonLocalDataSource.getVacanciesEntity().flowOn(coroutineDispatcher)
    }
}