package com.example.covitrack.repository

import com.example.covitrack.api.RetrofitInstance
import com.example.covitrack.model.CovidCases
import com.example.covitrack.model.SearchByDistrict
import com.example.covitrack.model.SearchByPinCode
import com.example.covitrack.model.SessionsItem

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
}