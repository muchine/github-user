package com.muchine.githubuser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.UserRepository
import com.muchine.githubuser.repository.source.remote.PagedUserLoader
import com.muchine.githubuser.ui.base.BaseViewModel

class PagedUserViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    private val config = Config(50, 15, false, 50)
    private val query = MutableLiveData<String>()

    var users = switchMap(query) {
        val factory = PagedUserLoader(repository, it, viewModelScope)
        LivePagedListBuilder<Int, User>(factory, config)
            .build()
    }

    fun fetchUsers(query: String) {
        this.query.value = query
    }

}