package com.example.cafeorderapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeorderapp.databinding.ItemPostBinding

class PostAdapter(private val list: ArrayList<PostResponse>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(jsonMentah: PostResponse) {
            val nilaijudulDariJSON = jsonMentah.title
            binding.itemRvTvJudul.text = nilaijudulDariJSON.rendered

            val nilaitanggalDariJSON = jsonMentah.date
            binding.itemRvTvTanggal.text = nilaitanggalDariJSON
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