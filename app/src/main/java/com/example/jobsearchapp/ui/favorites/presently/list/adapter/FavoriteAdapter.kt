package com.example.jobsearchapp.ui.favorites.presently.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearchapp.databinding.ItemVacanciesListBinding
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteState
import javax.inject.Inject

class FavoriteAdapter @Inject constructor(
    private val onClick: (FavoriteState) -> Unit,
    private val favoriteClick: (FavoriteState) -> Unit
) : RecyclerView.Adapter<FavoriteListHolder>()  {

    private var values:List<FavoriteState> = emptyList()

    fun setData(data: List<FavoriteState>){
        this.values = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListHolder {
        return FavoriteListHolder(
            binding = ItemVacanciesListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick,
            favoriteClick = favoriteClick
        )
    }

    override fun getItemCount(): Int  = values.size

    override fun onBindViewHolder(holder: FavoriteListHolder, position: Int) {
        val item = values.getOrNull(position)
        holder.bind(item)
    }
}