package com.kristof.weather.views.weather.forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.charts.Cartesian
import com.anychart.data.Set
import com.kristof.weather.MainApplication
import com.kristof.weather.R
import com.kristof.weather.models.*
import com.kristof.weather.presenters.UnitFormatter
import com.kristof.weather.presenters.UnitFormatter.format
import com.kristof.weather.presenters.WeatherForecastPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastFragment : Fragment(), IWeatherForecastScreen {
    @Inject
    lateinit var weatherPresenter: WeatherForecastPresenter

    private lateinit var textViewCity: TextView

    private lateinit var textViewAvgTemp: TextView
    private lateinit var textViewMinTemp: TextView
    private lateinit var textViewMaxTemp: TextView

    private lateinit var textViewAvgRain: TextView
    private lateinit var textViewTotalRain: TextView

    private lateinit var chartView: AnyChartView

    private var city: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)

        textViewCity = root.findViewById(R.id.tvCity)

        textViewAvgTemp = root.findViewById(R.id.tvAverageTemp)
        textViewMinTemp = root.findViewById(R.id.tvMinTemp)
        textViewMaxTemp = root.findViewById(R.id.tvMaxTemp)

        textViewAvgRain = root.findViewById(R.id.tvAverageRain)
        textViewTotalRain = root.findViewById(R.id.tvTotalRain)

        city = arguments?.getString("city")
        textViewCity.text = "$city weather forecast"

        chartView = root.findViewById(R.id.any_chart_view)
        chartView.setProgressBar(root.findViewById(R.id.progress_bar))
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
        chartView.clear()
        weatherPresenter.detachScreen()
    }

    override fun showWeather(weather: WeatherForecastSummary) {
        lifecycleScope.launch(Dispatchers.Main) {
            textViewAvgTemp.text = "${weather.avgTemp.format(2)} ${UnitFormatter.getTemperatureFormat(requireContext())}"
            textViewMinTemp.text = "${weather.minTemp.format(2)} ${UnitFormatter.getTemperatureFormat(requireContext())}"
            textViewMaxTemp.text = "${weather.maxTemp.format(2)} ${UnitFormatter.getTemperatureFormat(requireContext())}"

            textViewAvgRain.text = "${weather.avgRain.format(2)} mm"
            textViewTotalRain.text = "${weather.totalRain.format(2)} mm"
        }
    }

    override fun showChart(weather: List<HighLowChartData>) {
        lifecycleScope.launch(Dispatchers.Main) {
            val set = Set.instantiate()
            set.data(weather as List<DataEntry>?)
            val cartesian = initChart()
            var temperatureColumn = cartesian.rangeColumn(set.mapAs("{ x: 'date', high: 'high', low: 'low' }"))
            temperatureColumn.name(getString(R.string.temperature))
            chartView.setChart(cartesian)
        }
    }

    override fun showError(msg: String) {
        TODO("Not yet implemented")
    }

    private fun initChart(): Cartesian {
        val cartesian = AnyChart.cartesian()
        cartesian.title("Weekly weather forecast")
        cartesian.xAxis(true)
        cartesian.yAxis(true)
        cartesian.legend(true)
        return cartesian
    }
}