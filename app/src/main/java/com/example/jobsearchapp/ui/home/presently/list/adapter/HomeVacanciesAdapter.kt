package com.example.jobsearchapp.ui.home.presently.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearchapp.databinding.ItemListHomeBinding
import com.example.jobsearchapp.ui.home.presently.list.states.HomeState

class HomeAdapter(
    private val onClick: (HomeState) -> Unit
) : RecyclerView.Adapter<HomeListHolder>() {

    private var values:List<HomeState> = emptyList()

    fun setData(data: List<HomeState>){
        this.values = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListHolder {
       return HomeListHolder(
           binding = ItemListHomeBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       ))
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: HomeListHolder, position: Int) {
        val item = values.getOrNull(position)
        with(holder.binding){
            lookingNumber.text = textFormattingForLookingNumber(item?.lookingNumber)
//            image.load()
            title.text = item?.title
            address.text = item?.address?.town
            company.text = item?.company
            experience.text = item?.experience?.previewText
            publishedDate.text = item?.publishedDate
        }

        holder.binding.root.setOnClickListener{
            item?.let {
                onClick(item)
            }
        }
    }

    private fun textFormattingForLookingNumber(arg : Int?) : String {
        return when (arg) {
            null -> ""
            else -> "Cейчас просматривает $arg человек"
        }
    }
}