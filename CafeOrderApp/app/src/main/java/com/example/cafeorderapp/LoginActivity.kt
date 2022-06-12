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

        // Mulai binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orderButton.setOnClickListener {

            // Memasukkan data dari editTextName ke dalam variabel 'name;
            var name: String = binding.editTextName.text.toString().trim()

            // Memasukkan data dari editTextPassword ke dalam variabel 'password'
            var password: String = binding.editTextPassword.text.toString().trim()

            // Jika variabel 'name' dan 'password' tidak kosong, maka...
            if(name.isNotEmpty() && password.isNotEmpty()){
                val intent = Intent(this, CreateOrderActivity::class.java)

                // Menyiapkan data yang akan dibawa ke activity lain
                intent.putExtra("name", name)
                intent.putExtra("password", password)

                // Memulai activity lainnya
                startActivity(intent)
            }
            else{
                Toast.makeText(this, R.string.toast_login_button, Toast.LENGTH_SHORT).show()
            }
        }
    }
}