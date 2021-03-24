package com.kristof.weather.views.cities

import android.app.AlertDialog
import android.content.DialogInterface
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
import kotlinx.android.synthetic.main.add_city_dialog.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesActivity : AppCompatActivity(), ICitiesScreen {

    lateinit var adapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            showDialog()
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
            CitiesPresenter.getCities(applicationContext)
        }
    }

    private fun showDialog() {
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            val dialogView = this.layoutInflater.inflate(R.layout.add_city_dialog, null)
            builder.setView(dialogView).apply {
                setPositiveButton(R.string.ok) { _, _ -> addCity(dialogView.cityname.text.toString()) }
                setNegativeButton(R.string.cancel) { _, _ -> }
            }
            builder?.setTitle(R.string.dialog_title)
            builder.create()
        }
        alertDialog?.show()
    }

    private fun addCity(city: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            CitiesPresenter.addCity(city, applicationContext)
        }
    }

    private fun navigateToDetails() {
        startActivity(Intent(this, WeatherActivity::class.java))
    }

    override fun showCities(cityList: List<City>) {
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.setCityList(cityList)
        }
    }
}