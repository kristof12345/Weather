package com.kristof.weather.views.cities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kristof.weather.R
import com.kristof.weather.models.City
import kotlinx.android.synthetic.main.city.view.*

class CityListAdapter(private val context: Context) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {
    var cities = mutableListOf<City>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.tvName
        val temperature: TextView = view.tvTemp
        val image: ImageView = view.imageWeather
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.city, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.name.text = city.name
        var unit = " \u2103"
        holder.temperature.text = city.temperature.toUInt().toString() + unit
        val url =  "https://openweathermap.org/img/wn/" + city.weatherIcon + "@2x.png"
        Glide.with(context).load(url).into(holder.image);
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setCityList(cityList: List<City>) {
        cities.clear()
        cities.addAll(cityList)
        notifyDataSetChanged()
    }
}