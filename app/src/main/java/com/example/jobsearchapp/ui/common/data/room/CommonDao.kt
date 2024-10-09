package com.example.jobsearchapp.ui.common.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CommonDao {
    @Query("SELECT * FROM VacanciesEntity")
    fun getALL(): Flow<List<VacanciesEntity>>

    @Insert(entity = VacanciesEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<VacanciesEntity>)

    @Query("DELETE FROM VacanciesEntity")
    suspend fun delete()

    @Update
    suspend fun changeFavorite (entity: VacanciesEntity)
}