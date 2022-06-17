package com.example.cafeorderapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeorderapp.databinding.LayarBerandaBinding
import com.example.cafeorderapp.retrofit.RetrofitClient
import com.example.cafeorderapp.retrofit.kumpulanDataJSONBeritaTerbaru
import com.example.cafeorderapp.retrofit.kumpulanDataJSONPendidikan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Beranda : AppCompatActivity() {

    private val kumpulanDataBeritaTerbaru = ArrayList<kumpulanDataJSONBeritaTerbaru>()
    private val kumpulanDataPendidikan = ArrayList<kumpulanDataJSONBeritaTerbaru>()
    private val kumpulanDataPeristiwa = ArrayList<kumpulanDataJSONBeritaTerbaru>()

    private lateinit var binding: LayarBerandaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TESD","Masuk ke sesi Debugging.")
        binding = LayarBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layout1RvTerbaru.setHasFixedSize(true)
        binding.layout1RvTerbaru.layoutManager = LinearLayoutManager(this)
            Toast.makeText(this, "Tes Klik RV", Toast.LENGTH_SHORT).show()

        binding.layout1RvPendidikan.setHasFixedSize(true)
        binding.layout1RvPendidikan.layoutManager = LinearLayoutManager(this)

        binding.layout1RvPeristiwa.setHasFixedSize(true)
        binding.layout1RvPeristiwa.layoutManager = LinearLayoutManager(this)



        RetrofitClient.instance.ambilBeritaTerbaru(5,1,"id,title,date",null).enqueue(
            object: Callback<ArrayList<kumpulanDataJSONBeritaTerbaru>>{

                override fun onResponse(
                    call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>,
                    response: Response<ArrayList<kumpulanDataJSONBeritaTerbaru>>
                ) {
    //                val responseCode = response.code().toString()
                    response.body()?.let { kumpulanDataBeritaTerbaru.addAll(it)}

                    binding.layout1RvTerbaru.adapter = AdapterBeritaTerbaru(kumpulanDataBeritaTerbaru)
//                    val adapterdua = AdapterTemplate02(list)
//                    binding.layout1RvPost2.adapter = adapterdua
                }

                override fun onFailure(call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>, t: Throwable) {
                }
            }

        )

        RetrofitClient.instance.ambilBeritaPendidikan(5,1,"id,title,date",20).enqueue(
            object: Callback<ArrayList<kumpulanDataJSONBeritaTerbaru>>{

                override fun onResponse(
                    call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>,
                    response: Response<ArrayList<kumpulanDataJSONBeritaTerbaru>>
                ) {
                    //                val responseCode = response.code().toString()
                    response.body()?.let { kumpulanDataPendidikan.addAll(it)}

                    binding.layout1RvPendidikan.adapter = AdapterTemplate02(kumpulanDataPendidikan)
                }

                override fun onFailure(call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>, t: Throwable) {
                }
            }

        )

        RetrofitClient.instance.ambilBeritaPeristiwa(5,1,"id,title,date",21).enqueue(
            object: Callback<ArrayList<kumpulanDataJSONBeritaTerbaru>>{

                override fun onResponse(
                    call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>,
                    response: Response<ArrayList<kumpulanDataJSONBeritaTerbaru>>
                ) {
                    //                val responseCode = response.code().toString()
                    response.body()?.let { kumpulanDataPeristiwa.addAll(it)}

                    binding.layout1RvPeristiwa.adapter = AdapterBeritaTerbaru(kumpulanDataPeristiwa)
                }

                override fun onFailure(call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>, t: Throwable) {
                }
            }

        )
    }
}