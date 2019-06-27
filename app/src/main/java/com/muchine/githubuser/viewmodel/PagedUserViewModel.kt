package com.muchine.githubuser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import com.muchine.githubuser.repository.User
import com.muchine.githubuser.repository.source.remote.GithubDataSourceFactory
import com.muchine.githubuser.repository.source.remote.PagedRemoteDataSourceFactory
import com.muchine.githubuser.ui.base.BaseViewModel

class PagedUserViewModel(

) : BaseViewModel() {

    private val remoteSource = GithubDataSourceFactory.create()
    private val query = MutableLiveData<String>()
    private val config = Config(50, 50, false, 50)

    var users = switchMap(query) {
        val factory = PagedRemoteDataSourceFactory(remoteSource, it, viewModelScope)
        LivePagedListBuilder<Int, User>(factory, config)
            .build()
    }

    fun fetchUsers(query: String) {
        this.query.value = query
    }

}