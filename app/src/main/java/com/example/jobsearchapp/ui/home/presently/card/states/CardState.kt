package com.example.jobsearchapp.ui.home.presently.card.states


data class CardState (
    val id: String = "",
    val lookingNumber: Int = 0,
    val title: String = "",
    val address: AddressCardState = AddressCardState(),
    val company: String = "",
    val experience: ExperienceCardState = ExperienceCardState(),
    val publishedDate: String = "",
    val isFavorite: Boolean = false,
    val salary: SalaryCardState = SalaryCardState(),
    val schedules: List<String>  = mutableListOf(),
    val appliedNumber: Int = 0,
    val description: String = "",
    val responsibilities : String = "",
    val questions: List<String> = mutableListOf()
)