package com.muchine.githubuser.repository.source.remote

import androidx.paging.PageKeyedDataSource
import com.muchine.githubuser.repository.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagedRemoteDataSource(
    private val source: RemoteDataSource,
    private val query: String,
    private val scope: CoroutineScope
): PageKeyedDataSource<Int, User>() {

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
            val response = source.findUsers(query, page, per)
            if (!response.isSuccessful) throw IllegalStateException("response is not successful")

            val items = response.body()?.items ?: throw IllegalStateException("response body is null")
            val users = items.map { User(it.id, it.name, it.avatarUrl, false) }

            onLoaded(users)
        }
    }

}