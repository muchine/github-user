package com.muchine.githubuser.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val users = MutableLiveData<List<User>>()
    val favorites = MutableLiveData<List<User>>()

    fun fetchUser(query: String) {
        viewModelScope.launch {
            users.value = repository.findUsers(query)
        }
    }

    fun fetchFavorite(query: String) {
        viewModelScope.launch {
            favorites.value = repository.findFavorites(query)
        }
    }

    fun onClickFavorite(user: User) {
        if (user.isFavorite) removeFavorite(user) else addFavorite(user)
    }

    private fun addFavorite(user: User) {
        viewModelScope.launch {
            val updated = repository.saveFavorite(user)
            updateUsers(updated)
            updateFavorites(updated)
        }
    }

    private fun removeFavorite(user: User) {
        viewModelScope.launch {
            val updated = repository.removeFavorite(user)
            updateUsers(updated)
            updateFavorites(updated)
        }
    }

    private fun updateUsers(updated: User) {
        val userList = users.value ?: return
        users.value = userList.map { if (it.id == updated.id) updated else it }
    }

    private fun updateFavorites(updated: User) {
        favorites.value = if (updated.isFavorite) {
            arrayListOf(updated) + (favorites.value ?: arrayListOf())
        } else {
            favorites.value?.filter { it.id != updated.id }
        }
    }

}