package com.example.projekt

import android.content.Context

class DataSource(val context: Context) {
    fun getZaposleniList(): Array<String> = context.resources.getStringArray(R.array.zaposleniVpisani)
}