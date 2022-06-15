package com.example.cafeorderapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeorderapp.databinding.LayarRuangTamuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RuangTamu : AppCompatActivity() {

//    val POSTS: String = "posts"
//    val PER_HALAMAN: String = "per_page"
//    val JUMLAH_HALAMAN: Int = 3


    private val list = ArrayList<PostResponse>()

    private lateinit var binding: LayarRuangTamuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayarRuangTamuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPost.setHasFixedSize(true)
        binding.rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<PostResponse>>{
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                response.body()?.let { list.addAll(it)}
                val adapter = PostAdapter(list)
                binding.rvPost.adapter = adapter

            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
            }
        })

        binding.ruanganggotaButton.setOnClickListener {
            val intent = Intent(this, RuangAnggota::class.java)
            startActivity(intent)
        }
    }
}