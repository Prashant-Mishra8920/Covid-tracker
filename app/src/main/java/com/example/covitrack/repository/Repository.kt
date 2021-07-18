package com.example.covitrack.repository

import androidx.lifecycle.LiveData
import com.example.covitrack.api.RetrofitInstance
import com.example.covitrack.model.CovidCases
import com.example.covitrack.model.SearchByDistrict
import com.example.covitrack.model.SearchByPinCode
import com.example.covitrack.model.SessionsItem
import com.example.covitrack.room.CovidDao
import com.example.covitrack.room.CovidDatabase

class Repository {
    suspend fun getVaccineByPinCode(pinCode:String,date:String):SearchByPinCode{
        return RetrofitInstance.api.getVaccineByPinCode(pinCode,date)
    }

    suspend fun getCovidCases():CovidCases{
        return RetrofitInstance.api.getCovidCases()
    }

    suspend fun getDistrict():SearchByDistrict{
        return RetrofitInstance.api.getDistrict()
    }

    fun getAllCases(db:CovidDatabase) = db.dao().readAllCases()

    suspend fun insertCases(db:CovidDatabase,covidCases: CovidCases){
        db.dao().insertAllCases(covidCases)
    }

    suspend fun deleteAllCases(db: CovidDatabase){
        db.dao().deleteAllCases()
    }
}