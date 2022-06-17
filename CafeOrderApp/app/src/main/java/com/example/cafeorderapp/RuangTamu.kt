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

    private val kumpulanDataJuduldanTanggal = ArrayList<kumpulanDataJSONBeritaTerbaru>()
    private val kumpulanDataPendidikan = ArrayList<kumpulanDataJSONPendidikan>()
    private val kumpulanDataPeristiwa = ArrayList<kumpulanDataJSONPeristiwa>()

    private lateinit var binding: LayarRuangTamuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TESD","Masuk ke sesi Debugging.")
        binding = LayarRuangTamuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layout1RvTerbaru.setHasFixedSize(true)
        binding.layout1RvTerbaru.layoutManager = LinearLayoutManager(this)

        binding.layout1RvPendidikan.setHasFixedSize(true)
        binding.layout1RvPendidikan.layoutManager = LinearLayoutManager(this)

        binding.layout1RvPeristiwa.setHasFixedSize(true)
        binding.layout1RvPeristiwa.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.ambilBeritaTerbaru(5,1,"title,date",null).enqueue(
            object: Callback<ArrayList<kumpulanDataJSONBeritaTerbaru>>{

                override fun onResponse(
                    call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>,
                    response: Response<ArrayList<kumpulanDataJSONBeritaTerbaru>>
                ) {
    //                val responseCode = response.code().toString()
                    response.body()?.let { kumpulanDataJuduldanTanggal.addAll(it)}

                    binding.layout1RvTerbaru.adapter = AdapterBeritaTerbaru(kumpulanDataJuduldanTanggal)
//
//                    val adapterdua = AdapterTemplate02(list)
//                    binding.layout1RvPost2.adapter = adapterdua
                }

                override fun onFailure(call: Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>, t: Throwable) {
                }
            }

        )

        RetrofitClient.instance.ambilBeritaPendidikan(5,1,"title,date",20).enqueue(
            object: Callback<ArrayList<kumpulanDataJSONPendidikan>>{

                override fun onResponse(
                    call: Call<ArrayList<kumpulanDataJSONPendidikan>>,
                    response: Response<ArrayList<kumpulanDataJSONPendidikan>>
                ) {
                    //                val responseCode = response.code().toString()
                    response.body()?.let { kumpulanDataPendidikan.addAll(it)}

                    binding.layout1RvPendidikan.adapter = AdapterTemplate02(kumpulanDataPendidikan)
                }

                override fun onFailure(call: Call<ArrayList<kumpulanDataJSONPendidikan>>, t: Throwable) {
                }
            }

        )

        RetrofitClient.instance.ambilBeritaPeristiwa(5,1,"title,date",21).enqueue(
            object: Callback<ArrayList<kumpulanDataJSONPeristiwa>>{

                override fun onResponse(
                    call: Call<ArrayList<kumpulanDataJSONPeristiwa>>,
                    response: Response<ArrayList<kumpulanDataJSONPeristiwa>>
                ) {
                    //                val responseCode = response.code().toString()
                    response.body()?.let { kumpulanDataPeristiwa.addAll(it)}

                    binding.layout1RvPeristiwa.adapter = AdapterBeritaPeristiwa(kumpulanDataPeristiwa)
                }

                override fun onFailure(call: Call<ArrayList<kumpulanDataJSONPeristiwa>>, t: Throwable) {
                }
            }

        )
    }
}