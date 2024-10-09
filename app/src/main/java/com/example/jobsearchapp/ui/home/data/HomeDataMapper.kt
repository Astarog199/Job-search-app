package com.example.jobsearchapp.ui.home.data

import com.example.jobsearchapp.ui.home.data.models.OffersDataEntity
import com.example.jobsearchapp.ui.home.data.models.ButtonDataEntity
import com.example.jobsearchapp.ui.home.data.models.dto.OffersDto
import com.example.jobsearchapp.ui.home.data.models.dto.ButtonDto

class HomeDataMapper {
//    fun toEntity(dto: HomeDto): DataEntity {
//        return DataEntity(
//            offers = toOffersEntity(dto.offers),
//            vacancies = toVacanciesDataEntityList(dto.vacancies)
//        )
//    }


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