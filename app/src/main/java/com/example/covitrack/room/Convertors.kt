package com.example.covitrack.room

import androidx.room.TypeConverter
import com.example.covitrack.model.CasesTimeSeriesItem
import com.example.covitrack.model.StatewiseItem
import com.example.covitrack.model.TestedItem
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class Convertors {
    @TypeConverter
    fun covToStr(casesTimeSeriesItem: List<CasesTimeSeriesItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<CasesTimeSeriesItem>>() {}.type
        return gson.toJson(casesTimeSeriesItem,type)
    }

    @TypeConverter
    fun strToCov(string: String):List<CasesTimeSeriesItem>{
        val gson = Gson()
        val json = JsonParser().parse(string)
        val type = object : TypeToken<List<CasesTimeSeriesItem>>() {}.type
        return gson.fromJson(json,type)
    }

    @TypeConverter
    fun stateToStr(statewiseItem: List<StatewiseItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<StatewiseItem>>() {}.type
        return gson.toJson(statewiseItem,type)
    }

    @TypeConverter
    fun strToState(string: String):List<StatewiseItem>{
        val gson = Gson()
        val json = JsonParser().parse(string)
        val type = object : TypeToken<List<StatewiseItem>>() {}.type
        return gson.fromJson(json,type)
    }

    @TypeConverter
    fun testToStr(testedItem: List<TestedItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<TestedItem>>() {}.type
        return gson.toJson(testedItem,type)
    }

    @TypeConverter
    fun strToTest(string: String):List<TestedItem>{
        val gson = Gson()
        val json = JsonParser().parse(string)
        val type = object : TypeToken<List<TestedItem>>() {}.type
        return gson.fromJson(json,type)
    }
}