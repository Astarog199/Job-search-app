package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.ButtonDataEntity
import com.example.jobsearchapp.ui.home.data.models.OffersDataEntity
import com.example.jobsearchapp.ui.home.domain.models.ButtonDomainEntity
import com.example.jobsearchapp.ui.home.domain.models.OffersDomainEntity


class HomeDomainMapper {
    fun toOffersDomainEntity(offers: OffersDataEntity): OffersDomainEntity {
        return OffersDomainEntity(
            id =  offers.id,
            title = offers.title,
            button = toButtonDomainEntity(offers.button),
            link = offers.link
        )
    }

    private fun toButtonDomainEntity(buttonDataEntity: ButtonDataEntity?): ButtonDomainEntity {
        return ButtonDomainEntity(
            text = buttonDataEntity?.text ?: ""
        )
    }
}