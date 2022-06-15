package com.example.cafeorderapp

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("posts?per_page=5")
    fun getPosts(): Call<ArrayList<PostResponse>>

//    @GET("posts")
//    fun getPosts(@Query("per_page") perPage : Int): Call<ArrayList<PostResponse>>
//
//    @GET("{posts}")
//    fun getPosts(@Path("posts") posts:String,
//                 @Query("per_page") per_page:String,
//                 @Query("jumlah_halaman") jumlah_halaman:Int): Call<ArrayList<PostResponse>>
}