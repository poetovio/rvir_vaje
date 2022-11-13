package com.example.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.projekt.databinding.ActivityRecycleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class recycle : AppCompatActivity() {

    private lateinit var binding: ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bazaLjudje: List<Uporabnik>? = null
        val zaposleni = arrayOf(String)

        CoroutineScope(Dispatchers.Main).launch {
            bazaLjudje = Baza.getDatabase(this@recycle).uporabnikDao().getAll()

            val recyclerView = binding.recajkler

            val adapter = ZaposlenAdapter(bazaLjudje)

            recyclerView.adapter = adapter

            binding.recajkler.adapter?.notifyDataSetChanged()
        }





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

private operator fun <T> Array<T>.plusAssign(s: String) {

}
