package com.muchine.githubuser.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muchine.githubuser.repository.UserRepository

object UserViewModelFactory : ViewModelProvider.Factory {

    private val repository = UserRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }

}