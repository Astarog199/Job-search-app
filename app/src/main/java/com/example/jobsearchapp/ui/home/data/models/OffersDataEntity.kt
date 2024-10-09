package com.example.jobsearchapp.ui.home.data.models

import kotlinx.serialization.Serializable

@Serializable
data class OffersDataEntity (
    val id: String?,
    val title: String?,
    val button: ButtonDataEntity?,
    val link: String?
)