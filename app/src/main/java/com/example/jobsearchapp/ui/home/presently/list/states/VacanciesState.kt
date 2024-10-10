package com.example.jobsearchapp.ui.home.presently.list.states

data class VacanciesState (
    val id: String = "",
    val lookingNumber: Int = 0,
    val title: String = "",
    val address: AddressState,
    val company: String = "",
    val experience: ExperienceState,
    val publishedDate: String = "",
    val isFavorite: Boolean = false,
    val salary: SalaryState,
    val schedules: List<String> = mutableListOf(),
    val appliedNumber: Int = 0,
    val description: String = "",
    val responsibilities : String = "",
    val questions: List<String> = mutableListOf()
)