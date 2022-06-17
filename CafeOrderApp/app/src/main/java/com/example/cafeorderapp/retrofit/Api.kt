package com.example.cafeorderapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("{jenis}")
    fun ambilJSON(
        @Path("jenis") jenis: String?,
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?,
        @Query("_fields") _fields: String?,
        @Query("categories") categories: Int?
    ) : Call<ArrayList<kumpulanDataJSONBeritaTerbaru>>

//    posts?per_page=3&categories=20

//    @GET("posts?per_page=3")
//    fun getPostsTwo(): Call<ArrayList<JSONResponse>>

//    https://javasatu.com/wp-json/wp/v2/posts?categories=15 > Olahraga
//    23 > POLRI
//    6 > Desa Kita
//    19 > Pemerintahan
//    20 > Pendidikan
//    3056 > Organisasi
//    5 > Covid-19
//    21 > Peristiwa


//    @GET("posts")
//    fun getPosts(@Query("per_page") perPage : Int): Call<ArrayList<PostResponse>>
//
//    @GET("{posts}")
//    fun getPosts(@Path("posts") posts:String,
//                 @Query("per_page") per_page:String,
//                 @Query("jumlah_halaman") jumlah_halaman:Int): Call<ArrayList<PostResponse>>
}