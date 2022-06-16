package com.example.cafeorderapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeorderapp.databinding.Item01Binding
import com.example.cafeorderapp.databinding.ItemPostBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TITLE, JAM PUBLISH
class AdapterTemplate01(private val list: ArrayList<dtJSONTitleAndDate>): RecyclerView.Adapter<AdapterTemplate01.AdapterTemplate01VH>() {
    inner class AdapterTemplate01VH(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonMentah: dtJSONTitleAndDate) {
            Log.d("TESD","isi jsonMentah= " + jsonMentah.date.toString())
            binding.itemRvTvJudul.text = jsonMentah.title.rendered

            val parsedDate = LocalDateTime.parse(jsonMentah.date, DateTimeFormatter.ISO_DATE_TIME)
            binding.itemRvTvTanggal.text = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))
            binding.tvLokasi.text = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTemplate01VH {
        return AdapterTemplate01VH(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AdapterTemplate01VH, position: Int) {
        holder.bind(list[position])
    }
    override fun getItemCount(): Int = list.size
}

// TITLE, JAM PUBLISH
class AdapterTemplate02(private val list: ArrayList<dtJSONTitleAndDate>): RecyclerView.Adapter<AdapterTemplate02.AdapterTemplate02VH>() {
    inner class AdapterTemplate02VH(val binding: Item01Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonMentah: dtJSONTitleAndDate) {

            binding.item01tvJudul.text = jsonMentah.title.rendered

            val parsedDate = LocalDateTime.parse(jsonMentah.date, DateTimeFormatter.ISO_DATE_TIME)
            binding.item01tvTaksonomi.text = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTemplate02.AdapterTemplate02VH {
        return AdapterTemplate02VH(Item01Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AdapterTemplate02.AdapterTemplate02VH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}