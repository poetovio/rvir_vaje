package com.example.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.projekt.databinding.ActivityMainBinding
import com.example.projekt.databinding.ActivityRecycleBinding
import javax.sql.DataSource

class recycle : AppCompatActivity() {

    private lateinit var binding: ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val zaposleniList = DataSource(this).getZaposleniList()

        val recyclerView = binding.recajkler
        recyclerView.adapter = ZaposlenAdapter(zaposleniList)

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