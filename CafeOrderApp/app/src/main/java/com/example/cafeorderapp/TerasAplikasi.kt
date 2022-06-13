package com.example.cafeorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cafeorderapp.databinding.LayarTerasAplikasiBinding

class TerasAplikasi : AppCompatActivity() {
    // Menggunakan 'lateinit' sebagai persiapan binding sebelum onCreate
    // - Masukkan Agenda pembelajaran 'Tentang lateinit'
    private lateinit var binding: LayarTerasAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mulai binding
        binding = LayarTerasAplikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.orderButton.setOnClickListener {

            // Memasukkan data dari editTextName ke dalam variabel 'name;
            var name: String = binding.editTextName.text.toString().trim()

            // Memasukkan data dari editTextPassword ke dalam variabel 'password'
            var password: String = binding.editTextPassword.text.toString().trim()

            // Jika variabel 'name' dan 'password' tidak kosong, maka...
            if(name.isNotEmpty() && password.isNotEmpty()){
                val intent = Intent(this, RuangAnggota::class.java)

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

        binding.otherButton.setOnClickListener {
            val intent = Intent(this, MejaPendaftaran::class.java)

            startActivity(intent)
        }

        binding.tamuButton.setOnClickListener {
            val intent = Intent(this,RuangTamu::class.java)

            startActivity(intent)
        }
    }
}