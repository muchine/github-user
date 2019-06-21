package com.muchine.githubuser.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.muchine.githubuser.repository.source.local.AppDatabase
import com.muchine.githubuser.repository.source.remote.GithubDataSourceFactory
import com.muchine.githubuser.repository.source.remote.response.UserItemResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@WorkerThread
class UserRepository(context: Context) {

    private val remoteSource = GithubDataSourceFactory.create()
    private val localSource = AppDatabase.getInstance(context).localSource()

    suspend fun saveFavorite(user: User): User = execute {
        User(user.id, user.name, user.imageUrl, true).also { localSource.save(it) }
    }

    suspend fun removeFavorite(user: User): User = execute {
        User(user.id, user.name, user.imageUrl, false).also { localSource.remove(user) }
    }

    suspend fun findFavorites(query: String = ""): List<User> = execute {
        if (query.isEmpty()) localSource.findAll() else localSource.findByName("%$query%")
    }

    suspend fun findUsers(query: String): List<User> = execute {
        val favoriteUsers = localSource.findAll()

        val response = remoteSource.findUsers(query)
        if (!response.isSuccessful) throw IllegalStateException("response is not successful")

        response.body()?.let { response ->
            response.items.map { createUser(it, favoriteUsers) }
        } ?: throw IllegalStateException("response body is null")
    }

    private fun createUser(item: UserItemResponse, favoriteUsers: List<User>): User {
        val isFavorite = favoriteUsers.any { item.id == it.id }
        return User(item.id, item.name, item.avatarUrl, isFavorite)
    }

    private suspend fun <T> execute(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO, block)
    }

}