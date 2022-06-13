package com.example.cafeorderapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeorderapp.databinding.ActivityTamuBinding

class TamuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTamuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTamuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}