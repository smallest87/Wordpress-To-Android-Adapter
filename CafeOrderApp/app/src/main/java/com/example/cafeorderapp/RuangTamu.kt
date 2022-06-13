package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.LayarRuangTamuBinding

class RuangTamu : AppCompatActivity() {

    private lateinit var binding: LayarRuangTamuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayarRuangTamuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}