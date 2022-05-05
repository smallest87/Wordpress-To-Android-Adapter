package com.example.criminalintent.viewmodels

import androidx.lifecycle.ViewModel
import com.example.criminalintent.Crime
import com.example.criminalintent.database.CrimeRepository

class CrimeListViewModel: ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    val crimesLiveData = crimeRepository.getListOfCrimesRepo()

    fun addCrime(crime: Crime){
        crimeRepository.addCrime(crime)
    }
}