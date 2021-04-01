package com.kristof.weather.views.weather.forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kristof.weather.MainApplication
import com.kristof.weather.R
import com.kristof.weather.models.City
import com.kristof.weather.models.DailyWeatherForecast
import com.kristof.weather.models.Location
import com.kristof.weather.presenters.WeatherForecastPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastFragment : Fragment(), IWeatherForecastScreen {

    @Inject
    lateinit var weatherPresenter: WeatherForecastPresenter

    var textView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)
        textView = root.findViewById(R.id.text_dashboard)
        val city = arguments?.getString("city")
        textView?.text = "$city weather forecast"
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        weatherPresenter.attachScreen(this)
        lifecycleScope.launch(Dispatchers.IO) {
            weatherPresenter.getDailyWeather(
                City("Budapest", Location(10.0, 20.0)),
                requireContext()
            )
        }
    }

    override fun onStop() {
        super.onStop()
        weatherPresenter.detachScreen()
    }

    override fun showWeather(weather: List<DailyWeatherForecast>) {
        lifecycleScope.launch(Dispatchers.Main) {
            //TODO: Display data
            textView?.text = weather.first().temp.toString()
        }
    }

    override fun showError(msg: String) {
        TODO("Not yet implemented")
    }
}