package com.example.toolsshop.detailes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toolsshop.categories.PerforatorCategoryActivity
import com.example.toolsshop.databinding.ActivityPerforatorDetailBinding

class PerforatorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerforatorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerforatorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = intent
        if(intent.hasExtra("title") && intent.hasExtra("info") && intent.hasExtra("resId")){
            val title: String = intent.getStringExtra("title").toString()
            val info: String = intent.getStringExtra("info").toString()
            val resId: Int = intent.getIntExtra("resId", -1)
            binding.textViewPerforatorTitle.text = title
            binding.textViewPerforatorInfo.text = info
            binding.imageViewPerforator.setImageResource(resId)
        }else{
            val backToCategoryIntent = Intent(this, PerforatorCategoryActivity::class.java)
            startActivity(backToCategoryIntent)
        }
    }
}