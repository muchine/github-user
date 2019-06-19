package com.muchine.githubuser.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.muchine.githubuser.repository.source.local.AppDatabase
import com.muchine.githubuser.repository.source.remote.GithubDataSourceFactory
import com.muchine.githubuser.repository.source.remote.response.UserItemResponse

@WorkerThread
class UserRepository(context: Context) {

    private val remoteSource = GithubDataSourceFactory.create()
    private val localSource = AppDatabase.getInstance(context).localSource()

    suspend fun saveFavorite(user: User): User {
        val favorite = User(user.id, user.name, user.imageUrl, true)
        localSource.save(favorite)

        return favorite
    }

    suspend fun removeFavorite(user: User): User {
        localSource.remove(user)
        return User(user.id, user.name, user.imageUrl, false)
    }

    suspend fun findFavorites(query: String = ""): List<User> {
        return if (query.isEmpty()) localSource.findAll() else localSource.findByName("%$query%")
    }

    suspend fun findUsers(query: String): List<User> {
        val favoriteUsers = localSource.findAll()

        val response = remoteSource.findUsers(query)
        if (!response.isSuccessful) throw IllegalStateException("response is not successful")

        response.body()?.let { response ->
            return response.items.map { createUser(it, favoriteUsers) }
        } ?: throw IllegalStateException("response body is null")
    }

    private fun createUser(item: UserItemResponse, favoriteUsers: List<User>): User {
        val isFavorite = favoriteUsers.any { item.id == it.id }
        return User(item.id, item.name, item.avatarUrl, isFavorite)
    }

}