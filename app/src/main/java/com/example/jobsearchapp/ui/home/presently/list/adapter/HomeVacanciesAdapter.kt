package com.example.jobsearchapp.ui.home.presently.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearchapp.databinding.ItemVacanciesListBinding
import com.example.jobsearchapp.ui.home.presently.list.states.VacanciesState

class HomeVacanciesAdapter(
    private val onClick: (VacanciesState) -> Unit,
    private val favoriteClick: (VacanciesState) -> Unit
) : RecyclerView.Adapter<HomeVacanciesListHolder>() {

    private var values:List<VacanciesState> = emptyList()

    fun setData(data: List<VacanciesState>){
        this.values = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVacanciesListHolder {
       return HomeVacanciesListHolder(
           binding = ItemVacanciesListBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       ),
           onClick = onClick,
           favoriteClick = favoriteClick
       )
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: HomeVacanciesListHolder, position: Int) {
        val item = values.getOrNull(position)
        holder.bind(item)
    }
}