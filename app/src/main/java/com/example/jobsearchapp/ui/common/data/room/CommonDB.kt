package com.example.jobsearchapp.ui.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VacanciesEntity::class], version = 1)
abstract class CommonDB: RoomDatabase(){
    abstract fun commonDao(): CommonDao
}