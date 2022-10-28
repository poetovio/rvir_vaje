package com.example.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spolSpinner = findViewById<Spinner>(R.id.spinner2)

        val gumbPoslji = findViewById<Button>(R.id.buttonPoslji)

        spolSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spol,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        gumbPoslji.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Povzetek::class.java
                )
            )
        }
    }
}