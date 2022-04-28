package com.example.criminalintent

import android.content.Context
import java.lang.IllegalStateException

class CrimeRepository private constructor(context: Context) {

    companion object{
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = CrimeRepository(context)
            }
        }
    }

    fun get(): CrimeRepository{
        return INSTANCE?:
        throw IllegalStateException("CrimeRepository must be initialized")
    }
}