package com.example.beatboxapp

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBoxClass"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager){

    fun loadSounds(): List<String>{
        return try {
            val soundNames = assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG, "Found ${soundNames.size} sounds")
            soundNames.asList()
        } catch (e: Exception){
            Log.e(TAG, "Could not find list assets", e)
            emptyList()
        }
    }
}