package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.room.CommonDao
import com.example.jobsearchapp.ui.common.data.room.VacanciesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonLocalDataSource @Inject constructor(private val dao: CommonDao) {

    fun getVacanciesEntity(): Flow<List<VacanciesEntity>> {
        return dao.getALL()
    }

    suspend fun save(vacanciesEntity: List<VacanciesEntity>) {
        dao.insert(vacanciesEntity)
    }

    suspend fun changeFavoriteState(entity: VacanciesEntity) {
        dao.changeFavorite(entity = entity)
    }
}