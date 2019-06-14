package com.muchine.githubuser.repository

import androidx.lifecycle.MutableLiveData
import com.muchine.githubuser.repository.source.GithubDataSourceFactory
import com.muchine.githubuser.repository.source.response.UserListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val remoteSource = GithubDataSourceFactory.create()

    val users = MutableLiveData<List<User>>()

    fun fetch(query: String) {
        remoteSource.findUsers(query).enqueue(object : Callback<UserListResponse> {
            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (!response.isSuccessful) return

                users.value = response.body()?.let { response ->
                    response.items.map { User(it.name, it.avatarUrl) }
                } ?: return
            }
        })
    }

}