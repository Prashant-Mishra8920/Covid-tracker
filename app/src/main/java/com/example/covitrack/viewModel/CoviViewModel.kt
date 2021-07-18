package com.example.covitrack.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covitrack.model.CovidCases
import com.example.covitrack.model.SearchByDistrict
import com.example.covitrack.model.SearchByPinCode
import com.example.covitrack.model.SessionsItem
import com.example.covitrack.repository.Repository
import com.example.covitrack.room.CovidDao
import com.example.covitrack.room.CovidDatabase
import kotlinx.coroutines.launch

class CoviViewModel(private val repository: Repository):ViewModel() {
    val vaccineResponse:MutableLiveData<SearchByPinCode> = MutableLiveData()
    val covidResponse:MutableLiveData<CovidCases> = MutableLiveData()
    val vaccineDistrictResponse:MutableLiveData<SearchByDistrict> = MutableLiveData()

    fun getVaccineByPinCode(pinCode:String,date:String){
        viewModelScope.launch {
            val response = repository.getVaccineByPinCode(pinCode,date)
            vaccineResponse.value = response
        }
    }
    fun getCovidCases(){
        viewModelScope.launch {
            val response = repository.getCovidCases()
            covidResponse.value = response
        }
    }

    fun getDistrict(){
        viewModelScope.launch {
            val response = repository.getDistrict()
            vaccineDistrictResponse.value = response
        }
    }

    fun insertCases(db: CovidDatabase,covidCases: CovidCases){
        viewModelScope.launch {
            repository.insertCases(db,covidCases)
        }
    }

    fun getAllCases(db: CovidDatabase) = repository.getAllCases(db)

    fun delete(db:CovidDatabase){
        viewModelScope.launch {
            repository.deleteAllCases(db)
        }
    }



}