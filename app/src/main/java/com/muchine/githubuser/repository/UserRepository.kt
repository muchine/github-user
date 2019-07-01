package com.muchine.githubuser.repository

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import com.muchine.githubuser.repository.source.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@WorkerThread
class UserRepository(context: Context) {

    private val localSource = AppDatabase.getInstance(context).localSource()

    suspend fun saveFavorite(user: User): User = execute {
        User(user.id, user.name, user.imageUrl, true).also {
            localSource.save(it)
        }
    }

    suspend fun removeFavorite(user: User): User = execute {
        User(user.id, user.name, user.imageUrl, false).also {
            localSource.remove(user)
        }
    }

    suspend fun findFavorites(query: String = ""): List<User> = execute {
        Log.d("UserRepository", "find favorites... query: $query")
        if (query.isEmpty()) localSource.findAll() else localSource.findByName("%$query%")
    }

    private suspend fun <T> execute(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO, block)
    }

}
