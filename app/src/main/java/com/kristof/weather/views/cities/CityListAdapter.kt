package com.kristof.weather.views.cities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kristof.weather.R
import com.kristof.weather.models.City
import kotlinx.android.synthetic.main.city.view.*

class CityListAdapter(private val context: Context) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {
    var cities = mutableListOf<City>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.city, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.name.text = city.name
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}