package com.muchine.githubuser.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Long,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)