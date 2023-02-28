package com.example.onlineshop.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshop.domain.local.entities.Users

@Dao
interface AppDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun tryRegistration(userInfo: Users)

    @Query("SELECT password from users where firstName = :firstName")
    suspend fun tryLoginWithPassword(firstName: String): String?

    @Query("SELECT * from users where firstName = :firstName")
    suspend fun getAllUserInfo(firstName: String): Users?
}