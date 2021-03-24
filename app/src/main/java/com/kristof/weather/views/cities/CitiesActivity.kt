package com.kristof.weather.views.cities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kristof.weather.R
import com.kristof.weather.models.City
import com.kristof.weather.presenters.CitiesPresenter
import com.kristof.weather.views.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesActivity : AppCompatActivity(), ICitiesScreen {

    lateinit var adapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            navigate()
        }
    }

    override fun onStart() {
        super.onStart()
        CitiesPresenter.attachScreen(this);
    }

    override fun onStop() {
        super.onStop()
        CitiesPresenter.detachScreen();
    }

    override fun onResume() {
        super.onResume()
        adapter = CityListAdapter(this)
        cityList.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            CitiesPresenter.getCities()
        }
    }

    private fun navigate() {
        startActivity(Intent(this, WeatherActivity::class.java))
    }

    override fun showCities(cityList: List<City>) {
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.setCityList(cityList)
        }
    }
}