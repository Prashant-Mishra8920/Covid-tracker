package com.example.covitrack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.covitrack.model.CovidCases

@Database(entities = [CovidCases::class],version = 1,exportSchema = false)
@TypeConverters(Convertors::class)
abstract class CovidDatabase : RoomDatabase(){
    abstract fun dao():CovidDao

    companion object{
        @Volatile
        private var Instance:CovidDatabase? = null

        fun getDatabase(context: Context):CovidDatabase{
            return Instance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CovidDatabase::class.java,
                    "CovidDatabase"
                ).build()
                Instance = instance
                instance
            }
        }
    }
}