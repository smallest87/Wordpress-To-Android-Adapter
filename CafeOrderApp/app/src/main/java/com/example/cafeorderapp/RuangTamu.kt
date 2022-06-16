package com.example.cafeorderapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeorderapp.databinding.LayarRuangTamuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RuangTamu : AppCompatActivity() {

    private val list = ArrayList<dtJSONTitleAndDate>()

    private lateinit var binding: LayarRuangTamuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TESD","Masuk ke sesi Debugging.")
        binding = LayarRuangTamuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layout1RvPost.setHasFixedSize(true)
        binding.layout1RvPost.layoutManager = LinearLayoutManager(this)

        binding.layout1RvPost2.setHasFixedSize(true)
        binding.layout1RvPost2.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPosts(5,1,"title,date").enqueue(object: Callback<ArrayList<dtJSONTitleAndDate>>{
            override fun onResponse(
                call: Call<ArrayList<dtJSONTitleAndDate>>,
                response: Response<ArrayList<dtJSONTitleAndDate>>
            ) {
//                val responseCode = response.code().toString()
                response.body()?.let { list.addAll(it)}

                // mengisi adapter RecyclerView Post dengan PostAdapter yang telah diisi list
                val adapter = AdapterTemplate01(list)
                binding.layout1RvPost.adapter = adapter
                Log.d("TESD","Ukuran list= " + list.size.toString())

                val adapterdua = AdapterTemplate02(list)
                binding.layout1RvPost2.adapter = adapterdua
                Log.d("TESD","Pemasangan AdapterTemplate02")

            }

            override fun onFailure(call: Call<ArrayList<dtJSONTitleAndDate>>, t: Throwable) {
            }
        })

//        binding.ruanganggotaButton.setOnClickListener {
//            val intent = Intent(this, RuangAnggota::class.java)
//            startActivity(intent)
//        }
    }
}