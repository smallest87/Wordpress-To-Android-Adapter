package com.example.toolsshop

class Tool(private val title: String, private val info: String, private val imageResourceId: Int) {

    fun getTitle(): String {
        return title
    }
    fun getInfo(): String {
        return info
    }
    fun getImageResourceId(): Int {
        return imageResourceId
    }

    override fun toString(): String {
        return title
    }
}