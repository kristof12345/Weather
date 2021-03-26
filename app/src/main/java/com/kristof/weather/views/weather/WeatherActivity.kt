package com.kristof.weather.views.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kristof.weather.R

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        //Pass arguments to destination fragment
        val bundle = intent.extras;
        val city = bundle?.getString("city")
        val args = Bundle()
        args.putString("city", city);
        navView.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId, args)
            true
        }

        navController.popBackStack()
        navController.navigate(R.id.navigation_home, args)
    }
}