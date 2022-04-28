package com.example.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import com.example.criminalintent.Crime
import java.util.*

@Dao
interface CrimeDAO {
    @Query("SELECT * FROM crime")
    fun getCrimes(): List<Crime>

    @Query("SELECT * FROM crime WHERE id = (:id)")
    fun getCrime(id: UUID): Crime?
}