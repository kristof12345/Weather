package com.kristof.weather.views.cities

import android.R.attr.action
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kristof.weather.R
import com.kristof.weather.views.weather.WeatherActivity


class CitiesActivity : AppCompatActivity() {

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

    private fun navigate() {
        startActivity(Intent(this, WeatherActivity::class.java))
    }
}