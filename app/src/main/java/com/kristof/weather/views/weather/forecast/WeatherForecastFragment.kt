package com.kristof.weather.views.weather.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kristof.weather.R
import com.kristof.weather.models.WeatherForecast

class WeatherForecastFragment : Fragment(), IWeatherForecastScreen {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        val city = arguments?.getString("city")
        textView.text = city + " weather forecast"
        return root
    }

    override fun showWeather(weather: WeatherForecast) {
        
    }
}