package com.example.projekt

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt.databinding.ZaposlenBinding

class ZaposlenAdapter(val zaposleniList: List<Uporabnik>?): RecyclerView.Adapter<ZaposlenAdapter.ZaposleniViewHolder>() {

    class ZaposleniViewHolder(binding: ZaposlenBinding): RecyclerView.ViewHolder(binding.root) {
        private val zaposleniTextView = binding.imeRecikler

        fun bind(name: Uporabnik) {
            zaposleniTextView.text = name.ime + " " + name.priimek

            zaposleniTextView.setOnClickListener {
                Log.d("uporabnik", "Kliknili ste na uporabnika " + zaposleniTextView.text)
                val dialog = AlertDialog.Builder(zaposleniTextView.context)
                dialog.setTitle("Uporabnik")

                val builder = java.lang.StringBuilder()

                val ime = name.ime
                val priimek = name.priimek
                val spol = name.spol
                val datum = name.datumRojstva
                val prihod = name.prihod
                val odhod = name.odhod

                builder.append("Ime -> $ime\n")
                    .append("Priimek -> $priimek\n")
                    .append("Spol -> $spol\n")
                    .append("Datum rojstva -> $datum\n")
                    .append("Ura prihoda -> $prihod\n")
                    .append("Ura odhoda -> $odhod\n")

                dialog.setMessage(builder.toString())

                dialog.setPositiveButton("Zapri", DialogInterface.OnClickListener { _, _ -> })
                dialog.show()

            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZaposleniViewHolder =
        ZaposleniViewHolder(ZaposlenBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ZaposleniViewHolder, position: Int) = holder.bind(
        zaposleniList?.get(position) ?: throw IllegalArgumentException("name expected")
    )


    override fun getItemCount(): Int = zaposleniList?.size ?: -1
}