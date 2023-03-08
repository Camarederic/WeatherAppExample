package com.example.weatherappexample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappexample.DayItem
import com.example.weatherappexample.R
import com.example.weatherappexample.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class WeatherAdapter : ListAdapter<DayItem, WeatherAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)
        fun bind(item: DayItem) = with(binding) {
            tvDate.text = item.time
            tvCondition.text = item.condition
            tvTemp.text = item.currentTemp
            Picasso.get()
                .load("https:" + item.imageUrl)
                .into(image)
        }
    }

    class Comparator : DiffUtil.ItemCallback<DayItem>() {

        override fun areItemsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}