package com.example.jobsearchapp.ui.home.presently.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearchapp.R
import com.example.jobsearchapp.ui.home.presently.states.VacanciesState
import com.example.jobsearchapp.databinding.ItemVacanciesListBinding as Item

class HomeVacanciesListHolder(
    val binding:Item,
    private val onClick: (VacanciesState) -> Unit,
    private val favoriteClick: (VacanciesState) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: VacanciesState?){
        with(binding) {
            lookingNumber.text = textFormattingForLookingNumber(item?.lookingNumber)

            getImage(item?.isFavorite)?.let { imageButton.setImageResource(it) }
            title.text = item?.title
            address.text = item?.address?.town
            company.text = item?.company
            experience.text = item?.experience?.previewText
            publishedDate.text = item?.publishedDate
        }

        binding.root.setOnClickListener{
            item?.let {
                onClick(item)
            }
        }

        binding.imageButton.setOnClickListener {
            item?.let {
                favoriteClick(item)
            }
        }
    }


    private fun textFormattingForLookingNumber(arg : Int?) : String {
        return when (arg) {
            null -> ""
            else -> "Cейчас просматривает $arg человек"
        }
    }

    private fun getImage (isFavorite: Boolean?) : Int? {
        return when (isFavorite) {
            true -> R.drawable.active_favorite
            else -> R.drawable.favorite
        }
    }
}