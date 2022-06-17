package com.example.cafeorderapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeorderapp.databinding.Item01Binding
import com.example.cafeorderapp.databinding.ItemPostBinding
import com.example.cafeorderapp.retrofit.kumpulanDataJSONBeritaTerbaru
import com.example.cafeorderapp.retrofit.kumpulanDataJSONPendidikan
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TITLE, JAM PUBLISH
class AdapterBeritaTerbaru(private val list: ArrayList<kumpulanDataJSONBeritaTerbaru>): RecyclerView.Adapter<AdapterBeritaTerbaru.AdapterBeritaTerbaruVH>() {

    inner class AdapterBeritaTerbaruVH(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonMentah: kumpulanDataJSONBeritaTerbaru) {
            binding.itemRvTvJudul.text = jsonMentah.title.rendered

            val parsedDate = LocalDateTime.parse(jsonMentah.date, DateTimeFormatter.ISO_DATE_TIME)
            binding.itemRvTvTanggal.text = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))
            binding.tvLokasi.text = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBeritaTerbaruVH {
        return AdapterBeritaTerbaruVH(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AdapterBeritaTerbaruVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,list[position].title.rendered,Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int = list.size
}

// TITLE, JAM PUBLISH
class AdapterTemplate02(private val listdua: ArrayList<kumpulanDataJSONBeritaTerbaru>): RecyclerView.Adapter<AdapterTemplate02.AdapterTemplate02VH>() {
    inner class AdapterTemplate02VH(val binding: Item01Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonMentah: kumpulanDataJSONBeritaTerbaru) {

            binding.item01tvJudul.text = jsonMentah.title.rendered

            val parsedDate = LocalDateTime.parse(jsonMentah.date, DateTimeFormatter.ISO_DATE_TIME)
            binding.item01tvTaksonomi.text = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTemplate02.AdapterTemplate02VH {
        return AdapterTemplate02VH(Item01Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AdapterTemplate02.AdapterTemplate02VH, position: Int) {
        holder.bind(listdua[position])
    }

    override fun getItemCount(): Int = listdua.size

}
