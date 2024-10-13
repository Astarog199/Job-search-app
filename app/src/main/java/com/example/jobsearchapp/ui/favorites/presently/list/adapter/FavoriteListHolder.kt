package com.example.jobsearchapp.ui.favorites.presently.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearchapp.R
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteState
import com.example.jobsearchapp.databinding.ItemVacanciesListBinding as Item

class FavoriteListHolder(
    val binding: Item,
    private val onClick: (FavoriteState) -> Unit,
    private val favoriteClick: (FavoriteState) -> Unit
) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: FavoriteState?){
        with(binding){
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

    private fun getImage (isFavorite: Boolean?) : Int {
        return when (isFavorite) {
            true -> R.drawable.active_favorite
            else -> R.drawable.favorite
        }
    }
}