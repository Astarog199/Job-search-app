package com.example.jobsearchapp.ui.favorites.presently.list.states

import com.example.jobsearchapp.ui.common.domain.models.AddressCommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.ExperienceDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.SalaryDomainEntity


class FavoriteStateMapper {
    fun toFavoriteState(vacancies: CommonDomainEntity): FavoriteState {
        return FavoriteState(
            id = vacancies.id,
            lookingNumber =  vacancies.lookingNumber?: 0,
            title = vacancies.title,
            address = toAddressStateEntity(vacancies.address),
            company = vacancies.company,
            experience = toExperienceStateEntity(vacancies.experience),
            publishedDate = vacancies.publishedDate,
            isFavorite = vacancies.isFavorite,
            salary = toSalaryStateEntity(vacancies.salary),
            schedules = vacancies.schedules,
            appliedNumber = vacancies.appliedNumber ?: 0,
            description = vacancies.description ?: "",
            responsibilities = vacancies.responsibilities,
            questions = vacancies.questions
        )
    }

    private fun toAddressStateEntity(address: AddressCommonDomainEntity): AddressState {
        return AddressState(
            town = address.town,
            street = address.street,
            house = address.house
        )
    }

    private fun toExperienceStateEntity(experience: ExperienceDomainEntity) : ExperienceState {
        return ExperienceState(
            previewText = experience.previewText,
            text = experience.text
        )
    }

    private fun toSalaryStateEntity(salary: SalaryDomainEntity) : SalaryState {
        return SalaryState(full = salary.full)
    }
}