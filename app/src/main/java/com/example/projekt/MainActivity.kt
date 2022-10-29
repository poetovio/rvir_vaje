package com.example.projekt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.gumb_date).setOnClickListener {
            DatePickerDialog(this, this, koledar.get(Calendar.YEAR), koledar.get(Calendar.MONTH), koledar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val spolSpinner = findViewById<Spinner>(R.id.spinner2)

        val gumbPoslji = findViewById<Button>(R.id.buttonPoslji)

        findViewById<Button>(R.id.gumb_prihod).setOnClickListener {
            val koledarPrihod = Calendar.getInstance()

            val timestamp = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                koledarPrihod.set(Calendar.HOUR_OF_DAY, hour)
                koledarPrihod.set(Calendar.MINUTE, minute)

                findViewById<TextView>(R.id.prihod).text = SimpleDateFormat("HH:mm").format(koledarPrihod.time)
            }

            TimePickerDialog(this, timestamp, koledarPrihod.get(Calendar.HOUR_OF_DAY), koledarPrihod.get(Calendar.MINUTE), true).show()
        }

        findViewById<Button>(R.id.gumb_odhod).setOnClickListener {
            val koledarOdhod = Calendar.getInstance()

            val timestamp = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                koledarOdhod.set(Calendar.HOUR_OF_DAY, hour)
                koledarOdhod.set(Calendar.MINUTE, minute)

                findViewById<TextView>(R.id.odhod).text = SimpleDateFormat("HH:mm").format(koledarOdhod.time)
            }

            TimePickerDialog(this, timestamp, koledarOdhod.get(Calendar.HOUR_OF_DAY), koledarOdhod.get(Calendar.MINUTE), true).show()
        }

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