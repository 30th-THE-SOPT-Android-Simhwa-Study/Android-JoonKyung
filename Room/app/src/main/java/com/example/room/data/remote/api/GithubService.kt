package com.example.room.data.remote.api

import com.example.room.data.remote.github.ResponseGitHubFollower
import com.example.room.data.remote.github.ResponseRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/followers")
    suspend fun getGithubFollower(
        @Path("username") username: String
    ): List<ResponseGitHubFollower>

    @GET("users/{username}/repos")
    suspend fun getRepository(
        @Path("username") username: String
    ): List<ResponseRepo>
}