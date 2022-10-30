package com.example.projekt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.projekt.databinding.ActivityRecycleBinding

class recycle : AppCompatActivity() {

    private lateinit var binding: ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val zaposleniList = intent.getStringArrayExtra("vneseniZaposleni")

        val recyclerView = binding.recajkler

        val adapter = ZaposlenAdapter(zaposleniList)

        recyclerView.adapter = adapter

        binding.recajkler.adapter?.notifyDataSetChanged()


        findViewById<Button>(R.id.vracanjeDomov).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
    }
}