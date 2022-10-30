package com.example.projekt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt.databinding.ZaposlenBinding

class ZaposlenAdapter(val zaposleniList: Array<String>): RecyclerView.Adapter<ZaposlenAdapter.ZaposleniViewHolder>() {

    class ZaposleniViewHolder(binding: ZaposlenBinding): RecyclerView.ViewHolder(binding.root) {
        private val zaposleniTextView = binding.imeRecikler

        fun bind(name: String) {
            zaposleniTextView.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZaposleniViewHolder =
        ZaposleniViewHolder(ZaposlenBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ZaposleniViewHolder, position: Int) = holder.bind(zaposleniList[position])

    override fun getItemCount(): Int = zaposleniList.size
}