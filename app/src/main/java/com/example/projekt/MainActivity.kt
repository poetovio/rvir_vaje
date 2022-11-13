package com.example.projekt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val koledar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN)

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.gumb_date).setOnClickListener {
            DatePickerDialog(this, this, koledar.get(Calendar.YEAR), koledar.get(Calendar.MONTH), koledar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val spolSpinner = findViewById<Spinner>(R.id.spinner2)

        val gumbPoslji = findViewById<Button>(R.id.buttonPoslji)

        val gumbPrikazi = findViewById<Button>(R.id.gumb_prikaz)

        var vneseniZaposleni = DataSource(this).getZaposleniList()

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
            val intent = Intent(this, Povzetek::class.java)

            val ime = findViewById<EditText>(R.id.editTextTextPersonName3).text.toString()
            val priimek = findViewById<EditText>(R.id.editTextTextPersonName4).text.toString()
            val spol = findViewById<Spinner>(R.id.spinner2).selectedItem.toString()
            val datum = findViewById<TextView>(R.id.textView6).text.toString()
            val prihod = findViewById<TextView>(R.id.prihod).text.toString()
            val odhod = findViewById<TextView>(R.id.odhod).text.toString()

            vneseniZaposleni += (findViewById<EditText>(R.id.editTextTextPersonName3).text.toString() + " " + findViewById<EditText>(R.id.editTextTextPersonName4).text.toString())

            coroutineScope.launch {
                Baza.getDatabase(this@MainActivity).uporabnikDao().insertAll(Uporabnik(0, ime, priimek, spol, datum, prihod, odhod))
                Toast.makeText(applicationContext, "Uporabnik " + ime + " " + priimek + " je bil uspešno dodan v bazo.", Toast.LENGTH_SHORT).show()
            }

            intent.putExtra("ime", ime)
            intent.putExtra("priimek", findViewById<EditText>(R.id.editTextTextPersonName4).text.toString())
            intent.putExtra("spol", findViewById<Spinner>(R.id.spinner2).selectedItem.toString())
            intent.putExtra("datum", findViewById<TextView>(R.id.textView6).text.toString())
            intent.putExtra("prihod", findViewById<TextView>(R.id.prihod).text.toString())
            intent.putExtra("odhod", findViewById<TextView>(R.id.odhod).text.toString())

            startActivity(intent)
        }

        gumbPrikazi.setOnClickListener {
            val intent = Intent(this, recycle::class.java)

            intent.putExtra("vneseniZaposleni", vneseniZaposleni)

            startActivity(intent)
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