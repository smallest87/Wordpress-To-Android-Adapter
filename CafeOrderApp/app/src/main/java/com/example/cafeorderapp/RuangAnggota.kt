package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.LayarRuangAnggotaBinding

class RuangAnggota : AppCompatActivity() {

    private lateinit var binding: LayarRuangAnggotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayarRuangAnggotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}