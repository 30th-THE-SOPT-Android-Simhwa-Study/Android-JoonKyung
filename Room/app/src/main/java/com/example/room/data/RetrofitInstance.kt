package com.example.room.data

import com.example.room.data.remote.api.GithubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val GITHUB_BASE_URL = "http://api.github.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val GITHUB_SERVICE: GithubService = githubRetrofit.create(GithubService::class.java)
}