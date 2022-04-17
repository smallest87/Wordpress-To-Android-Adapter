package com.example.toolsshop.detailes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toolsshop.categories.AngleGrinderCategoryActivity
import com.example.toolsshop.databinding.ActivityAngleGrinderDetailBinding

class AngleGrinderDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAngleGrinderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAngleGrinderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = intent
        if(intent.hasExtra("title") && intent.hasExtra("info") && intent.hasExtra("resId")){
            val title: String = intent.getStringExtra("title").toString()
            val info: String = intent.getStringExtra("info").toString()
            val resId: Int = intent.getIntExtra("resId", -1)
            binding.textViewAngleGrinderTitle.text = title
            binding.textViewAngleGrinderInfo.text = info
            binding.imageViewAngleGrinder.setImageResource(resId)
        }else{
            val backToCategoryIntent = Intent(this, AngleGrinderCategoryActivity::class.java)
            startActivity(backToCategoryIntent)
        }
    }
}