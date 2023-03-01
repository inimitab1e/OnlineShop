package com.example.onlineshop.domain.local

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

    @Query("SELECT EXISTS(SELECT * FROM users where email = :email)")
    suspend fun checkIfUserExists(email: String): Boolean

    @Query("SELECT password from users where email = :email")
    suspend fun tryLoginWithPassword(email: String): String?

    @Query("SELECT * from users where email = :email")
    fun getAllUserInfo(email: String): Flow<Users>?

    @Query("DELETE from users where email = :email")
    suspend fun deleteUserInfo(email: String)
}