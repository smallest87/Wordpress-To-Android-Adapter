package com.example.beatboxapp

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBoxClass"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager){

    private val sounds: List<Sound>

    init {
        sounds = loadSounds()
    }

    fun loadSounds(): List<Sound>{

        val soundNames: Array<String>
         try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception){
            Log.e(TAG, "Could not find list assets", e)
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { fileName ->
            val assetPath = "$SOUNDS_FOLDER/$fileName"
            val sound = Sound(assetPath)
        }

        return sounds
    }
}