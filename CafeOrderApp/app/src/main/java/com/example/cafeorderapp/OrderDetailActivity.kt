package com.example.cafeorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafeorderapp.databinding.ActivityOrderDetailBinding

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        if(intent.hasExtra("order")){
            binding.textViewOrder.text = intent.getStringExtra("order")
        }else{
            var backToLoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(backToLoginIntent)
        }

    }
}