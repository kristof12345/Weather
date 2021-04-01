package com.kristof.weather.views.weather.current

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.charts.Cartesian
import com.anychart.data.Set
import com.bumptech.glide.Glide
import com.kristof.weather.MainApplication
import com.kristof.weather.R
import com.kristof.weather.models.ChartData
import com.kristof.weather.models.City
import com.kristof.weather.models.CurrentWeather
import com.kristof.weather.presenters.WeatherPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherFragment : Fragment(), ICurrentWeatherScreen {
    @Inject
    lateinit var weatherPresenter: WeatherPresenter

    private lateinit var textViewCity: TextView
    private lateinit var textViewWind: TextView
    private lateinit var textViewPressure: TextView
    private lateinit var textViewTemp: TextView
    private lateinit var textViewRain: TextView
    private lateinit var textViewVisibility: TextView
    private lateinit var imageView: ImageView

    private lateinit var chartView: AnyChartView
    private lateinit var cartesian: Cartesian

    private var city: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_current, container, false)

        textViewCity = root.findViewById(R.id.tvCity)
        textViewTemp = root.findViewById(R.id.tvTemp)
        imageView = root.findViewById(R.id.imageWeather)
        textViewRain = root.findViewById(R.id.tvRain)
        textViewVisibility = root.findViewById(R.id.tvVis)
        textViewWind = root.findViewById(R.id.tvWind)
        textViewPressure = root.findViewById(R.id.tvPressure)

        city = arguments?.getString("city")
        textViewCity.text = "$city current weather"

        chartView = root.findViewById(R.id.any_chart_view)
        chartView.setProgressBar(root.findViewById(R.id.progress_bar))
        initChart()
        return root
    }

    private fun initChart() {
        cartesian = AnyChart.cartesian()
        cartesian.title("Daily weather forecast")
        cartesian.xAxis(true)
        cartesian.yAxis(true)
        cartesian.legend(true)
        cartesian.tooltip().titleFormat("{%SeriesName} ({%x})")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        weatherPresenter.attachScreen(this)
        lifecycleScope.launch(Dispatchers.IO) {
            weatherPresenter.getCurrentWeather(City(city!!), requireContext())
            weatherPresenter.getHourlyWeather(City(city!!), requireContext())
        }
    }

    override fun onStop() {
        super.onStop()
        weatherPresenter.detachScreen()
    }

    override fun showWeather(weather: CurrentWeather) {
        lifecycleScope.launch(Dispatchers.Main) {
            textViewTemp.text = weather.main.temp.toString()
            textViewWind.text = weather.wind.speed.toString()
            textViewPressure.text = weather.main.pressure.toString()
            textViewRain.text = weather.main.humidity.toString()
            textViewVisibility.text = weather.visibility.toString()
            val url =
                "https://openweathermap.org/img/wn/" + weather.weather.first().icon + "@2x.png"
            Glide.with(requireContext()).load(url).into(imageView)

        }
    }

    override fun showChart(weather: List<ChartData>) {
        lifecycleScope.launch(Dispatchers.Main) {
            val set = Set.instantiate()
            set.data(weather as List<DataEntry>?)
            var temperatureColumn =
                cartesian.rangeColumn(set.mapAs("{ x: 'date', high: 'high', low: 'low' }"))
            temperatureColumn.name(getString(R.string.temperature))
            chartView.setChart(cartesian)
        }
    }

    override fun showError(msg: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
    }
}