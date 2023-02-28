package com.example.onlineshop.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshop.domain.local.entities.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDatabaseDAO {

    @Insert()
    suspend fun tryRegistration(userInfo: Users)

    @Query("SELECT EXISTS(SELECT * FROM users where firstName = :firstName)")
    suspend fun checkIfUserExists(firstName: String): Boolean

    @Query("SELECT password from users where firstName = :firstName")
    suspend fun tryLoginWithPassword(firstName: String): String?

    @Query("SELECT * from users where firstName = :firstName")
    suspend fun getAllUserInfo(firstName: String): Flow<Users>?
}