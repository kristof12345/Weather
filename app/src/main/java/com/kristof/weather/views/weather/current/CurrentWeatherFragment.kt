package com.kristof.weather.views.weather.current

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
import com.kristof.weather.models.ChartData
import com.kristof.weather.models.City
import com.kristof.weather.models.DailyWeather
import com.kristof.weather.presenters.WeatherPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherFragment : Fragment(), ICurrentWeatherScreen {
    @Inject
    lateinit var weatherPresenter: WeatherPresenter

    private lateinit var textView: TextView
    private lateinit var chartView: AnyChartView
    private lateinit var cartesian: Cartesian

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_current, container, false)
        textView = root.findViewById(R.id.text_home)
        val city = arguments?.getString("city")
        textView.text = "$city current weather"

        chartView = root.findViewById(R.id.any_chart_view)
        chartView.setProgressBar(root.findViewById(R.id.progress_bar))
        initChart()
        showChart()
        return root
    }

    private fun initChart() {
        cartesian = AnyChart.cartesian()
        cartesian.title("Temperature")
        cartesian.xAxis(true)
        cartesian.yAxis(true)
        cartesian.legend(true)
        cartesian.yGrid(true).yMinorGrid(true)
        cartesian.tooltip().titleFormat("{%SeriesName} ({%x})")
    }

    private fun showChart() {
        var data = mutableListOf<ChartData>()
        data.add(ChartData("Jan", 5.8, 4.9))

        val set = Set.instantiate()
        set.data(data as List<DataEntry>?)
        val londonData = set.mapAs("{ x: 'date', high: 'high', low: 'low' }")

        var columnLondon = cartesian.rangeColumn(londonData)
        columnLondon.name("London")
        chartView.setChart(cartesian)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MainApplication).injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        weatherPresenter.attachScreen(this)
        lifecycleScope.launch(Dispatchers.IO) {
            weatherPresenter.getWeather(City("Budapest"), requireContext())
        }
    }

    override fun onStop() {
        super.onStop()
        weatherPresenter.detachScreen()
    }

    override fun showWeather(weather: DailyWeather) {
        lifecycleScope.launch(Dispatchers.Main) {
            //TODO: Display data
            textView.text = weather.main.humidity.toString()
            showChart()
        }
    }

    override fun showError(msg: String) {
        TODO("Not yet implemented")
    }
}