package com.example.weatherappexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.weatherappexample.DayItem
import com.example.weatherappexample.R
import com.example.weatherappexample.adapters.WeatherAdapter
import com.example.weatherappexample.databinding.FragmentHoursBinding


class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        recyclerView.adapter = adapter
        val list = listOf(
            DayItem(
                "",
                "12:00",
                "Sunny",
                "25℃",
                "",
                "",
                "",
                ""
            ),
            DayItem(
                "",
                "13:00",
                "Sunny",
                "27℃",
                "",
                "",
                "",
                ""
            ),
            DayItem(
                "",
                "14:00",
                "Sunny",
                "35℃",
                "",
                "",
                "",
                ""
            )
        )
        adapter.submitList(list)
    }

    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}