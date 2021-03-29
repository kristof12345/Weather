package com.kristof.weather

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

fun Context.getDefaultSharedPreferences(): SharedPreferences {
    val name = this.packageName + "_preferences"
    return this.getSharedPreferences(name, MODE_PRIVATE)
}