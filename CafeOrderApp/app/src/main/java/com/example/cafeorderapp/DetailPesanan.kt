package com.example.cafeorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafeorderapp.databinding.LayarDetailPesananBinding

class DetailPesanan : AppCompatActivity() {

    private lateinit var binding: LayarDetailPesananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayarDetailPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        if(intent.hasExtra("order")){
            binding.textViewOrder.text = intent.getStringExtra("order")
        }else{
            var backToLoginIntent = Intent(this, TerasAplikasi::class.java)
            startActivity(backToLoginIntent)
        }

    }
}