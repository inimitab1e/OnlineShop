package com.example.onlineshop.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.onlineshop.domain.local.entities.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDatabaseDAO {

    @Insert()
    suspend fun tryRegistration(userInfo: Users)

    @Query("SELECT EXISTS(SELECT * FROM users where firstName = :firstName)")
    suspend fun checkIfUserExists(firstName: String): Boolean

    @Query("SELECT * from users where firstName = :firstName")
    fun getAllUserInfo(firstName: String): Users
}