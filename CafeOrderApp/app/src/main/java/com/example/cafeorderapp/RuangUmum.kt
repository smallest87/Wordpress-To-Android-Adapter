package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.LayarRuangUmumBinding

class RuangUmum : AppCompatActivity() {

    lateinit var binding: LayarRuangUmumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayarRuangUmumBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}