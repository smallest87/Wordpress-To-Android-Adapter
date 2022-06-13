package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.LayarMejaPendaftaranBinding

class MejaPendaftaran : AppCompatActivity() {

    private lateinit var binding: LayarMejaPendaftaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayarMejaPendaftaranBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}