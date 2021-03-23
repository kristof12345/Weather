package com.kristof.weather.views.cities

import android.R.attr.action
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kristof.weather.R
import com.kristof.weather.models.City
import com.kristof.weather.presenters.CitiesPresenter
import com.kristof.weather.views.weather.WeatherActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CitiesActivity : AppCompatActivity(), ICitiesScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        setSupportActionBar(findViewById(R.id.toolbar))

        val textView: TextView = findViewById(R.id.text_home)
        textView.text = "Cities Activity"
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            navigate()
        }
    }

    override fun onStart() {
        super.onStart()
        CitiesPresenter.attachScreen(this);
        lifecycleScope.launch(Dispatchers.IO) {
            CitiesPresenter.getCities()
        }
    }

    override fun onStop() {
        super.onStop()
        CitiesPresenter.detachScreen();
    }

    private fun navigate() {
        startActivity(Intent(this, WeatherActivity::class.java))
    }

    override fun showCities(citiesList: List<City>) {

    }
}