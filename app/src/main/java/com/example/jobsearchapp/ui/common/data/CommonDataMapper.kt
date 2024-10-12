package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.models.AddressDto
import com.example.jobsearchapp.ui.common.data.models.ExperienceDto
import com.example.jobsearchapp.ui.common.data.models.SalaryDto
import com.example.jobsearchapp.ui.common.data.models.VacanciesDto
import com.example.jobsearchapp.ui.common.data.room.VacanciesEntity

class CommonDataMapper {
    fun toVacanciesEntity(vacancies: VacanciesDto): VacanciesEntity {
        return VacanciesEntity(
            id = vacancies.id,
            lookingNumber = vacancies.lookingNumber,
            title = vacancies.title,
            address =  toAddressCommonEntity(vacancies.address),
            company = vacancies.company,
            experience = toExperienceCommonEntity(vacancies.experience),
            publishedDate = vacancies.publishedDate,
            isFavorite = vacancies.isFavorite,
            salary = toSalaryCommonEntity(vacancies.salary),
            schedules =  fromList(vacancies.schedules),
            appliedNumber = vacancies.appliedNumber,
            description = vacancies.description,
            responsibilities = vacancies.responsibilities,
            questions =  fromList(vacancies.questions)
        )
    }

    private fun toAddressCommonEntity(address: AddressDto): String {
        var text = ""
        if (address.town.isNotEmpty()){
            text += address.town + ", "
        }

        if (address.street.isNotEmpty()){
            text += address.town + ", "
        }

        if (address.house.isNotEmpty()){
            text += address.town
        }

        return text
    }

    private fun toExperienceCommonEntity(experience: ExperienceDto) : String {
        return experience.previewText + ", " + experience.text
    }

    private fun toSalaryCommonEntity(salary: SalaryDto) : String {
        return  salary.full
    }

    private fun fromList(schedules: List<String?>): String {
        return schedules.joinToString (", ")
    }

    private fun toList(inputString: String): List<String> {
        return inputString.split(", ")
    }
}