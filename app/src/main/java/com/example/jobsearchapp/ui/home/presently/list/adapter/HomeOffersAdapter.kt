package com.example.jobsearchapp.ui.home.presently.list.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearchapp.R
import com.example.jobsearchapp.databinding.ItemOffersHomeBinding
import com.example.jobsearchapp.ui.home.presently.list.states.OffersState
import com.example.jobsearchapp.ServiceLocator.applicationContext


class HomeOffersAdapter : RecyclerView.Adapter<HomeOffersListHolder>() {

    private var values:List<OffersState> = emptyList()

    fun setData(data: List<OffersState>){
        this.values = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOffersListHolder {
        return HomeOffersListHolder(
            binding = ItemOffersHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: HomeOffersListHolder, position: Int) {
        val item = values.getOrNull(position)

        with(holder.binding) {

            getImage(item?.id)?.let { icon.setImageResource(it) }
            textView.text = item?.title
            button.text = item?.button?.text ?: ""
        }
    }

    private fun getImage(id: String?): Int? {
        return when(id){
            "near_vacancies" ->  R.drawable.near_vacancies
            "level_up_resume" ->  R.drawable.level_up_resume
            "temporary_job" ->  R.drawable.temporary_job
            else -> null
        }
    }
}