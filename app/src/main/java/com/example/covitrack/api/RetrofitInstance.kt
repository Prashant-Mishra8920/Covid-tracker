package com.example.covitrack.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://cdn-api.co-vin.in/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:coviApi by lazy {
        retrofit.create(coviApi::class.java)
    }
}