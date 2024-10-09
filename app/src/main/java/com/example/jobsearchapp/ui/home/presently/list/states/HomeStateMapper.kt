package com.example.jobsearchapp.ui.home.presently.list.states

import com.example.jobsearchapp.ui.common.domain.models.AddressCommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.CommonDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.ExperienceDomainEntity
import com.example.jobsearchapp.ui.common.domain.models.SalaryDomainEntity
import com.example.jobsearchapp.ui.home.domain.models.ButtonDomainEntity
import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity

class HomeStateMapper {
    fun toHomeStateEntity(vacancies: CommonDomainEntity): HomeState {
        return HomeState(
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

    fun toOffersHomeStateEntity(offer: OffersDomainEntity): OffersState {
        return OffersState(
            id = offer.id,
            title = offer.title,
            button = toButtonState(offer.button),
            link = offer.link
        )
    }

    private fun toButtonState(button: ButtonDomainEntity): ButtonState {
        return ButtonState(text = button.text)
    }
}