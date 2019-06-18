package com.muchine.githubuser.repository.source.remote

import com.google.gson.GsonBuilder
import com.muchine.githubuser.BuildConfig
import com.muchine.githubuser.repository.source.remote.response.UserListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("search/users")
    suspend fun findUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per: Int = 100
    ): Response<UserListResponse>

}

object GithubDataSourceFactory {

    private val httpClient = createHttpClient()

    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    private val source = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(RemoteDataSource::class.java)

    fun create(): RemoteDataSource = source

    private fun createHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return builder.build()
    }

}