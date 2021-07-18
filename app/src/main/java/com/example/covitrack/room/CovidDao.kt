package com.example.covitrack.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.covitrack.model.CovidCases

@Dao
interface CovidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCases(covidCases: CovidCases)

    @Query("Select * From covid_table")
    fun readAllCases():LiveData<CovidCases>

    @Query("Delete from covid_table")
    suspend fun deleteAllCases()
}