package com.example.jobsearchapp.ui.common.data.room

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromList(schedules: List<String?>): String {
        return schedules.joinToString (", ")
    }

    @TypeConverter
    fun toList(inputString: String): List<String> {
        return inputString.split(", ")
    }
}