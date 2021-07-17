package com.example.covitrack.api

import com.example.covitrack.model.CovidCases
import com.example.covitrack.model.SearchByDistrict
import com.example.covitrack.model.SearchByPinCode
import com.example.covitrack.model.SessionsItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface coviApi {
    @GET("appointment/sessions/public/findByPin")
    suspend fun getVaccineByPinCode(@Query("pincode")pincode:String,@Query("date")date:String):SearchByPinCode

    @GET("https://api.covid19india.org/data.json")
    suspend fun getCovidCases():CovidCases

    @GET("admin/location/districts/9")
    suspend fun getDistrict():SearchByDistrict
}