package com.example.jobsearchapp.ui.common.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VacanciesEntity")
data class VacanciesEntity (
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "lookingNumber") val lookingNumber: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "experience") val experience: String,
    @ColumnInfo(name = "publishedDate") val publishedDate: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "salary") val salary: String,
    @ColumnInfo(name = "schedules") val schedules: String,
    @ColumnInfo(name = "appliedNumber") val appliedNumber: Int?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "responsibilities") val responsibilities : String,
    @ColumnInfo(name = "questions") val questions: String
)
