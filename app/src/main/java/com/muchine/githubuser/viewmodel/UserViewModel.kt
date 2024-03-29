package com.muchine.githubuser.viewmodel

import androidx.lifecycle.MutableLiveData
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.ui.base.BaseViewModel

class UserViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    val users = MutableLiveData<List<User>>()
    val favorites = MutableLiveData<List<User>>()

    fun fetchUser(query: String) {
        execute {
            updateUsers(repository.findUsers(query))
        }
    }

    fun fetchFavorite(query: String = "") {
        execute {
            updateFavorites(repository.findFavorites(query))
        }
    }

    fun onClickFavorite(user: User) {
        if (user.isFavorite) removeFavorite(user) else addFavorite(user)
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
        val userList = users.value ?: return
        updateUsers(userList.map { if (it.id == updated.id) updated else it })
    }

    private fun updateFavorites(updated: User) {
        val favoriteList = (favorites.value ?: arrayListOf()).let {
            if (updated.isFavorite) it + updated else it.filter { item -> item.id != updated.id }
        }

        updateFavorites(favoriteList)
    }

    private fun updateUsers(updated: List<User>) {
        users.value = updated.sortedBy { it.name }
    }

    private fun updateFavorites(updated: List<User>) {
        favorites.value = updated.sortedBy { it.name }
    }

}