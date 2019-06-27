package com.muchine.githubuser.repository.source.remote

import androidx.paging.DataSource
import com.muchine.githubuser.repository.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class PagedRemoteDataSourceFactory(
    private val source: RemoteDataSource,
    private val query: String,
    private val scope: CoroutineScope = GlobalScope
) : DataSource.Factory<Int, User>() {
    override fun create(): DataSource<Int, User> {
        return PagedRemoteDataSource(source, query, scope)
    }
}