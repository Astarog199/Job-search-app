package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.models.CommonDto
import com.example.jobsearchapp.ui.common.domain.CommonRepository
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CommonRepositoryImpl(
    private val remoteDataSource: CommonRemoteDataSource,
    private val commonDataMapper: CommonDataMapper,
    private val commonDomainMapper: CommonDomainMapper,
    private val commonLocalDataSource: CommonLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher,
    ) : CommonRepository {
    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)
    private var dto = CommonDto(offers = emptyList(), vacancies = emptyList())

    init {
        scope.launch {
            getApiService()
            saveVacancies()
        }
    }

    override fun consumeVacancies(): Flow<List<CommonDomainEntity>> {
        return commonLocalDataSource.getVacanciesEntity()
            .map {
                it.map(commonDomainMapper::toCommonDomainEntity)
            }
    }

    override fun consumeFavoriteVacancies(): Flow<List<CommonDomainEntity>> {
        return commonLocalDataSource.getVacanciesEntity()
            .filter { favorites ->
                favorites.isEmpty()
            }
            .map { it.map(commonDomainMapper::toCommonDomainEntity) }
    }

    override suspend fun changeFavoriteState(id: String) {
        commonLocalDataSource.getVacanciesEntity().map {
            for (i in it){
                if (i.id == id) {
                   val res = i.copy(isFavorite = !i.isFavorite)
                    commonLocalDataSource.changeFavoriteState(res)
                    break
                }
            }
        }

    }

    private suspend fun getApiService() {
        dto = remoteDataSource.getDTO()
    }

    private suspend fun saveVacancies() {
        val vacanciesEntity = dto.vacancies.map(commonDataMapper::toVacanciesEntity)
        commonLocalDataSource.save(vacanciesEntity)
    }
}