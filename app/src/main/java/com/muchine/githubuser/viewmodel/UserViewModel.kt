package com.muchine.githubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.repository.source.remote.PagedUserLoader
import com.muchine.githubuser.ui.base.BaseViewModel

class UserViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    private val config = Config(50, 15, false, 50)
    private val userQuery = MutableLiveData<String>()
    private var factory: PagedUserLoader? = null

    val users = createUsersLiveData()
    val favorites = MutableLiveData<List<User>>()

    fun fetchUsers(query: String) {
        this.userQuery.value = query
    }

    fun fetchFavorite(query: String = "") {
        execute {
            updateFavorites(repository.findFavorites(query))
        }
    }

    fun onClickFavorite(user: User) {
        if (user.isFavorite) removeFavorite(user) else addFavorite(user)
    }

    private fun createUsersLiveData(): LiveData<PagedList<User>> {
        return switchMap(userQuery) {
            val factory = PagedUserLoader(repository, it, viewModelScope)
            this.factory = factory
            LivePagedListBuilder<Int, User>(factory, config).build()
        }
    }

    private fun addFavorite(user: User) {
        execute {
            val updated = repository.saveFavorite(user)
            updateUsers(updated)
            updateFavorites(updated)
        }
    }

    private fun removeFavorite(user: User) {
        execute {
            val updated = repository.removeFavorite(user)
            updateUsers(updated)
            updateFavorites(updated)
        }
    }

    private fun updateUsers(updated: User) {
        factory?.updateUser(updated) ?: return
    }

    private fun updateFavorites(updated: User) {
        val favoriteList = (favorites.value ?: arrayListOf()).let {
            if (updated.isFavorite) it + updated else it.filter { item -> item.id != updated.id }
        }

        updateFavorites(favoriteList)
    }

    private fun updateFavorites(updated: List<User>) {
        favorites.value = updated.sortedBy { it.name }
    }

}