package com.muchine.githubuser.viewmodels

import androidx.lifecycle.ViewModel
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var users = repository.users

    fun fetch(query: String) {
       repository.fetch(query)
    }

    fun getItemCount(): Int {
        return users.value?.size ?: 0
    }

    fun getItem(position: Int): User {
        return users.value?.get(position) ?: throw IllegalStateException()
    }

}