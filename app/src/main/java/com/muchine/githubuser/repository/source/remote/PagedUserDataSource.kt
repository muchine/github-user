package com.muchine.githubuser.repository.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.repository.source.remote.response.UserItemResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PagedUserDataSource(
    private val repository: UserRepository,
    private val users: MutableList<User>,
    private val query: String,
    private val scope: CoroutineScope
): PageKeyedDataSource<Int, User>() {

    private val server = GithubDataSourceFactory.create()

    // TODO: Observer this
    val state = MutableLiveData<DataSourceState>()

    init {
        state.postValue(DataSourceState.LOADED)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        load(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        load(params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        load(params.key, params.requestedLoadSize) {
            callback.onResult(it, params.key - 1)
        }
    }

    private fun load(page: Int, per: Int, onLoaded: suspend (users: List<User>) -> Unit) {
        scope.launch {
            withContext(Dispatchers.IO) {
                state.postValue(DataSourceState.LOADING)

                val index = (page - 1) * per
                if (index + per > users.size) fetchUsers(page, per)

                val items = users.slice(index until index + per)

                onLoaded(items)
                state.postValue(DataSourceState.LOADED)
            }
        }
    }

    private suspend fun fetchUsers(page: Int, per: Int): List<User> {
        val response = server.findUsers(query, page, per)
        if (!response.isSuccessful) throw IllegalStateException("response is not successful")

        val favorites = repository.findFavorites()
        val items = response.body()?.let { body ->
            body.items.map { createUser(it, favorites) }.sortedBy { it.name }
        } ?: throw IllegalStateException("response body is null")

        Log.d("PagedUserDataSource", "user size: ${users.size}, items size: ${items.size}")
        users.addAll(items)
        Log.d("PagedUserDataSource", "item added. Now ${users.size} items in the users.")
        return items
    }

    private fun createUser(item: UserItemResponse, favoriteUsers: List<User>): User {
        val isFavorite = favoriteUsers.any { item.id == it.id }
        return User(item.id, item.name, item.avatarUrl, isFavorite)
    }

}

enum class DataSourceState {
    LOADING, LOADED
}