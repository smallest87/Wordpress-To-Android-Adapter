package com.example.cafeorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cafeorderapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orderButton.setOnClickListener {
            var name: String = binding.editTextName.text.toString().trim()
            var password: String = binding.editTextPassword.text.toString().trim()
            if(name.isNotEmpty() && password.isNotEmpty()){
                val intent = Intent(this, CreateOrderActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("password", password)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, R.string.toast_login_button, Toast.LENGTH_SHORT).show()
            }
        }
    }
}