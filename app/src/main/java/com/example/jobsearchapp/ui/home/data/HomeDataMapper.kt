package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.OffersDataEntity
import com.example.jobsearchapp.ui.home.data.models.ButtonDataEntity
import com.example.jobsearchapp.ui.home.data.models.dto.OffersDto
import com.example.jobsearchapp.ui.home.data.models.dto.ButtonDto
import javax.inject.Inject

class HomeDataMapper @Inject constructor(){

    fun toOffersDataEntity(offers: OffersDto): OffersDataEntity {
        return OffersDataEntity(
            id = offers.id,
            title = offers.title,
            button = offers.button?.let { toButtonDataEntity(it) },
            link = offers.link
        )
    }

    private fun toButtonDataEntity(button: ButtonDto): ButtonDataEntity {
        return ButtonDataEntity(
            text = button.text
        )
    }
}