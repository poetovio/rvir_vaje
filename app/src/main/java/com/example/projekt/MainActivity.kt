package com.example.projekt

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val koledar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.gumb_date).setOnClickListener {
            DatePickerDialog(this, this, koledar.get(Calendar.YEAR), koledar.get(Calendar.MONTH), koledar.get(Calendar.DAY_OF_MONTH)).show()
        }

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

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        koledar.set(year, month, dayOfMonth)
        prikaziDatum(koledar.timeInMillis)
    }

    private fun prikaziDatum(timestamp: Long) {
        findViewById<TextView>(R.id.textView6).text = formatter.format(timestamp)
    }
}