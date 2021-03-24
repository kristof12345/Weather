package com.kristof.weather.views.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kristof.weather.R
import com.kristof.weather.models.DailyWeather
import com.kristof.weather.presenters.CitiesPresenter
import com.kristof.weather.presenters.WeatherPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment(), ICurrentWeatherScreen {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_current, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        val city = arguments?.getString("city")
        textView.text = city + " current weather"
        return root
    }

    override fun onStart() {
        super.onStart()
        WeatherPresenter.attachScreen(this);
        lifecycleScope.launch(Dispatchers.IO) {
            //WeatherPresenter.getWeather("Budapest")
        }
    }

    override fun onStop() {
        super.onStop()
        CitiesPresenter.detachScreen();
    }

    override fun showWeather(weather: DailyWeather) {

    }
}