package com.example.jobsearchapp.ui.home.presently.card.states

import com.example.jobsearchapp.ui.common.domain.models.AddressCommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.ExperienceDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.SalaryDomainEntity

class CardStateMapper {
    fun toHomeCardState(vacancies: CommonDomainEntity): CardState {
        return CardState(
            id = vacancies.id,
            lookingNumber = vacancies.lookingNumber ?: 0,
            title = vacancies.title,
            address = toAddressCardState(vacancies.address),
            company = vacancies.company,
            experience = toExperienceCardState(vacancies.experience),
            publishedDate = vacancies.publishedDate,
            isFavorite = vacancies.isFavorite,
            salary = toSalaryCardState(vacancies.salary),
            schedules = vacancies.schedules,
            appliedNumber = vacancies.appliedNumber ?: 0,
            description = vacancies.description ?: "",
            responsibilities = vacancies.responsibilities,
            questions = vacancies.questions
        )
    }

    private fun toAddressCardState(address: AddressCommonDomainEntity): AddressCardState {
        return AddressCardState(
            town = address.town,
            street = address.street,
            house = address.house
        )
    }

    private fun toExperienceCardState(experience: ExperienceDomainEntity) : ExperienceCardState {
        return ExperienceCardState(
            previewText = experience.previewText,
            text = experience.text
        )
    }

    private fun toSalaryCardState(salary: SalaryDomainEntity) : SalaryCardState {
        return SalaryCardState(full = salary.full)
    }
}