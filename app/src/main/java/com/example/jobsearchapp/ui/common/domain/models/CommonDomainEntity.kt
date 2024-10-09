package com.example.jobsearchapp.ui.common.domain.models

data class CommonDomainEntity (
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: AddressCommonDomainEntity,
    val company: String,
    val experience: ExperienceDomainEntity,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDomainEntity,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities : String,
    val questions: List<String>
)