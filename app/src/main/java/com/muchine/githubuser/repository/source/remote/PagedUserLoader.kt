package com.muchine.githubuser.repository.source.remote

import androidx.paging.DataSource
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class PagedUserLoader(
    private val repository: UserRepository,
    private val query: String,
    private val scope: CoroutineScope = GlobalScope
) : DataSource.Factory<Int, User>() {

    private val users = ArrayList<User>()
    private lateinit var source: PagedUserDataSource

    override fun create(): DataSource<Int, User> {
        val source = PagedUserDataSource(repository, users, query, scope).also { source = it }
        this.source = source
        return source
    }

    fun updateUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        users[index] = user

        source.invalidate()
    }

}