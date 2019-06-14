package com.muchine.githubuser.repository.source

import com.google.gson.GsonBuilder
import com.muchine.githubuser.repository.source.response.UserListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("search/users")
    fun findUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per: Int = 100
    ): Call<UserListResponse>

}

object GithubDataSourceFactory {

    var gson = GsonBuilder()
        .serializeNulls()
        .create()

    private val source = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(RemoteDataSource::class.java)

    fun create() = source

}