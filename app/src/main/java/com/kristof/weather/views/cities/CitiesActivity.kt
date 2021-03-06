package com.kristof.weather.views.cities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.kristof.weather.MainApplication
import com.kristof.weather.R
import com.kristof.weather.models.City
import com.kristof.weather.presenters.CitiesPresenter
import com.kristof.weather.views.settings.SettingsActivity
import com.kristof.weather.views.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.add_city_dialog.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesActivity : AppCompatActivity(), ICitiesScreen {
    @Inject
    lateinit var citiesPresenter: CitiesPresenter
    lateinit var adapter: CityListAdapter
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> navigateToSettings()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContentView(R.layout.activity_cities)
        setSupportActionBar(findViewById(R.id.toolbar))

        (application as MainApplication).injector.inject(this)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            showDialog()
        }

        val callback = TouchHelper(this)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.cityList))
    }

    override fun onStart() {
        super.onStart()
        citiesPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        citiesPresenter.detachScreen()
    }

    override fun onResume() {
        super.onResume()
        adapter = CityListAdapter(this, this)
        cityList.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            citiesPresenter.getCities(applicationContext)
        }

        firebaseAnalytics.logEvent("Open_app") {}
    }

    private fun showDialog() {

        //throw RuntimeException("Test Crash") // Force a crash

        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            val dialogView = this.layoutInflater.inflate(R.layout.add_city_dialog, null)
            builder.setView(dialogView).apply {
                setPositiveButton(R.string.ok) { _, _ -> addCity(dialogView.city_name.text.toString()) }
                setNegativeButton(R.string.cancel) { _, _ -> }
            }
            builder.setTitle(R.string.add_city_dialog_title)
            builder.create()
        }
        alertDialog?.show()
    }

    private fun addCity(city: String) {
        firebaseAnalytics.logEvent("Add_favourite_city") { param("city_name", city) }
        lifecycleScope.launch(Dispatchers.IO) {
            citiesPresenter.addCity(city, applicationContext)
        }
    }

    override fun deleteCity(position: Int) {
        val city = adapter.cities[position]

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.METHOD, "Delete_city")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle)

        lifecycleScope.launch(Dispatchers.IO) {
            citiesPresenter.deleteCity(city.name, applicationContext)
        }
    }

    override fun navigateToDetails(city: City) {
        val args = Bundle()
        args.putString("city", city.name)
        var intent = Intent(this, WeatherActivity::class.java)
        intent.putExtras(args)
        startActivity(intent)
    }

    private fun navigateToSettings(): Boolean {
        startActivity(Intent(this, SettingsActivity::class.java))
        return true
    }

    override fun showCities(citiesList: List<City>) {
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.setCityList(citiesList)
        }
    }

    override fun showError(msg: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
        }
    }
}