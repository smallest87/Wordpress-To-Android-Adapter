package com.example.toolsshop.detailes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toolsshop.categories.DrillCategoryActivity
import com.example.toolsshop.databinding.ActivityDrillDetailBinding

class DrillDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrillDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrillDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = intent
        if(intent.hasExtra("title") && intent.hasExtra("info") && intent.hasExtra("resId")){
            val title: String = intent.getStringExtra("title").toString()
            val info: String = intent.getStringExtra("info").toString()
            val resId: Int = intent.getIntExtra("resId", -1)
            binding.textViewDrillTitle.text = title
            binding.textViewDrillInfo.text = info
            binding.imageViewDrill.setImageResource(resId)
        }else{
            val backToCategoryIntent = Intent(this, DrillCategoryActivity::class.java)
            startActivity(backToCategoryIntent)
        }


    }
}