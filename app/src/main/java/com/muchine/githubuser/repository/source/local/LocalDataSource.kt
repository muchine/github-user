package com.muchine.githubuser.repository.source.local

import androidx.room.*
import com.muchine.githubuser.repository.User

@Dao
interface LocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Delete
    suspend fun remove(user: User)

    @Query("SELECT * FROM users ORDER BY name ASC")
    suspend fun findAll(): List<User>

    @Query("SELECT * FROM users WHERE name LIKE :name ORDER BY name ASC")
    suspend fun findByName(name: String): List<User>

}