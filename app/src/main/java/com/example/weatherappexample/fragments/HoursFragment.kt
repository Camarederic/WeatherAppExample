package com.example.weatherappexample.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.weatherappexample.DayItem
import com.example.weatherappexample.MainViewModel
import com.example.weatherappexample.R
import com.example.weatherappexample.adapters.WeatherAdapter
import com.example.weatherappexample.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject


class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

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

        model.liveDataCurrent.observe(viewLifecycleOwner) {
            Log.d("MyLog", "Hours: ${it.hours}")
            adapter.submitList(getHoursList(it))
        }
    }

    private fun initRecyclerView() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        recyclerView.adapter = adapter

    }

    private fun getHoursList(weatherItem: DayItem): List<DayItem> {
        val hoursArray = JSONArray(weatherItem.hours)
        val list = ArrayList<DayItem>()
        for (i in 0 until hoursArray.length()) {
            val item = DayItem(
                weatherItem.city,
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                ""
            )
            list.add(item)
        }
        return list
    }

    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}