package com.example.cafeorderapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://javasatu.com/wp-json/wp/v2/"

    val instance:Api by lazy {
        val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        retrofit.create(Api::class.java)
    }

}