package com.example.cafeorderapp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeorderapp.databinding.ItemPostBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PostAdapter(private val list: ArrayList<JSONResponse>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonMentah: JSONResponse) {
            val nilaijudulDariJSON = jsonMentah.title
            binding.itemRvTvJudul.text = nilaijudulDariJSON.rendered

            val nilaitanggalDariJSON = jsonMentah.date //masih tipe string

            val parsedDate = LocalDateTime.parse(nilaitanggalDariJSON, DateTimeFormatter.ISO_DATE_TIME)
            val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("eeee, HH:mm"))

            binding.itemRvTvTanggal.text = formattedDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }
    override fun getItemCount(): Int = list.size
}