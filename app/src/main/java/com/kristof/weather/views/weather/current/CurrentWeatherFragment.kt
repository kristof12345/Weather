package com.kristof.weather.views.weather.current

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
import com.kristof.weather.models.DailyWeather
import com.kristof.weather.presenters.WeatherPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherFragment : Fragment(), ICurrentWeatherScreen {
    @Inject
    lateinit var weatherPresenter: WeatherPresenter

    var textView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_current, container, false)
        textView = root.findViewById(R.id.text_home)
        val city = arguments?.getString("city")
        textView?.text = "$city current weather"
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        weatherPresenter.attachScreen(this);
        lifecycleScope.launch(Dispatchers.IO) {
            weatherPresenter.getWeather(City("Budapest"))
        }
    }

    override fun onStop() {
        super.onStop()
        weatherPresenter.detachScreen();
    }

    override fun showWeather(weather: DailyWeather) {
        lifecycleScope.launch(Dispatchers.Main) {
            //TODO: Display data
            textView?.text = weather.main.humidity.toString()
        }
    }

    override fun showError(msg: String) {
        TODO("Not yet implemented")
    }
}