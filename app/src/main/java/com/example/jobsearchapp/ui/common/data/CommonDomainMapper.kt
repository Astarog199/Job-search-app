package com.example.jobsearchapp.ui.common.data

import com.example.jobsearchapp.ui.common.data.room.VacanciesEntity
import com.example.jobsearchapp.ui.common.domain.models.AddressCommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.ExperienceDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.SalaryDomainEntity

class CommonDomainMapper {
    fun toCommonDomainEntity(vacancies: VacanciesEntity): CommonDomainEntity {
        return CommonDomainEntity(
            id = vacancies.id,
            lookingNumber = vacancies.lookingNumber,
            title = vacancies.title,
            address = toAddressDomainEntity(vacancies.address),
            company = vacancies.company,
            experience = toExperienceDomainEntity(vacancies.experience),
            publishedDate = vacancies.publishedDate,
            isFavorite = vacancies.isFavorite,
            salary = toSalaryDomainEntity(vacancies.salary),
            schedules = toList(vacancies.schedules),
            appliedNumber = vacancies.appliedNumber,
            description = vacancies.description,
            responsibilities = vacancies.responsibilities,
            questions = toList(vacancies.questions)
        )
    }

    private fun toAddressDomainEntity(address: String): AddressCommonDomainEntity {
        val text = address.split(", ")
        return AddressCommonDomainEntity(
            town = text[0],
            street = text[1],
            house = text[2]
        )
    }

    private fun toExperienceDomainEntity(experience: String) : ExperienceDomainEntity {
        val text = experience.split(", ")
        return ExperienceDomainEntity(
            previewText = text[0],
            text = text[1]
        )
    }

    private fun toSalaryDomainEntity(salary: String) : SalaryDomainEntity {
        return SalaryDomainEntity(full = salary)
    }

    private fun toList(inputString: String): List<String> {
        return inputString.split(", ")
    }

    fun toVacanciesEntity(vacancies: CommonDomainEntity): VacanciesEntity {
        return VacanciesEntity(
            id = vacancies.id,
            lookingNumber = vacancies.lookingNumber,
            title = vacancies.title,
            address = toAddressString(vacancies.address),
            company = vacancies.company,
            experience = toExperienceString(vacancies.experience),
            publishedDate = vacancies.publishedDate,
            isFavorite = vacancies.isFavorite,
            salary = toSalaryDomainEntity(vacancies.salary),
            schedules = fromList(vacancies.schedules),
            appliedNumber = vacancies.appliedNumber,
            description = vacancies.description,
            responsibilities = vacancies.responsibilities,
            questions = fromList(vacancies.questions)
        )
    }

    private fun toAddressString(address: AddressCommonDomainEntity): String {
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

    private fun toExperienceString(experience: ExperienceDomainEntity) : String {
        return experience.previewText + ", " + experience.text
    }

    private fun toSalaryDomainEntity(salary: SalaryDomainEntity) : String {
        return  salary.full
    }

    private fun fromList(schedules: List<String?>): String {
        return schedules.joinToString (", ")
    }
}