package com.example.cafeorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafeorderapp.databinding.ActivityCreateOrderBinding

class CreateOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.radioGroupDrinks.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.radioButtonTea){
                binding.textViewToppings.text
            }
            if (checkedId == R.id.radioButtonCoffee){

            }
        }
        binding.imageViewButton.setOnClickListener {

        }
    }
}