package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.LayarRuangDapurBinding

class RuangDapur : AppCompatActivity() {
    private lateinit var binding: LayarRuangDapurBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayarRuangDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}