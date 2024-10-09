package com.example.jobsearchapp.ui.common.data.room.otherModels

import androidx.room.ColumnInfo

data class AddressCommonEntity(
    @ColumnInfo(name = "town") val town: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "house") val house: String
)
