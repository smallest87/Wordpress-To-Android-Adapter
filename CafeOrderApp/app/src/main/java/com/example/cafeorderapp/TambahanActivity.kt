package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.ActivityTambahanBinding

class TambahanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}