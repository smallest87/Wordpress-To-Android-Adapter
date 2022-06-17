package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.LayarArtikelTunggalBinding
import com.example.cafeorderapp.databinding.LayarTerasAplikasiBinding

class ArtikelTunggal : AppCompatActivity() {

    private lateinit var binding: LayarArtikelTunggalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayarArtikelTunggalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}