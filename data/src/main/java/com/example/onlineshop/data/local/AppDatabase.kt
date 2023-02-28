package com.example.onlineshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.onlineshop.domain.local.AppDatabaseDAO
import com.example.onlineshop.domain.local.entities.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun AppDatabaseDAO(): AppDatabaseDAO
}