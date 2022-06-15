package com.example.cafeorderapp

import com.google.gson.annotations.SerializedName

data class PostResponse(
    val title: Renderan,
    val date: String?
)

data class Renderan (
    val rendered: String?
    )
//data class PostResponse(
//    @SerializedName("title")
//    val title: Title
//)
//data class Title (
//    val rendered: String?
//)