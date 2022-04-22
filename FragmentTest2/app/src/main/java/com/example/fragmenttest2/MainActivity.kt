package com.example.fragmenttest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmenttest2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        if (savedInstanceState == null){
            val fragment = PrimaryFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerPrimary, fragment)
                .commit()
        }
    }
}