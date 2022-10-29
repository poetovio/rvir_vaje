package com.example.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Povzetek : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_povzetek)

        val gumbNazaj = findViewById<Button>(R.id.gumb_nazaj)

        val prostor = findViewById<TextView>(R.id.textView2)

        val ime = intent.getStringExtra("ime")
        val priimek = intent.getStringExtra("priimek")
        val spol = intent.getStringExtra("spol")
        val datum = intent.getStringExtra("datum")
        val prihod = intent.getStringExtra("prihod")
        val odhod = intent.getStringExtra("odhod")

        val builder = java.lang.StringBuilder()

        builder.append("Ime -> $ime\n")
                .append("Priimek -> $priimek\n")
                .append("Spol -> $spol\n")
                .append("Datum rojstva -> $datum\n")
                .append("Ura prihoda -> $prihod\n")
                .append("Ura odhoda -> $odhod\n")

        prostor.text = builder.toString()

        gumbNazaj.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
    }
}