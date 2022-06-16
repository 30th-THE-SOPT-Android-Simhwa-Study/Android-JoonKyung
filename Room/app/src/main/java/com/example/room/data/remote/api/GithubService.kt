package com.example.room.data.remote.api

import com.example.room.data.remote.github.ResponseGitHubFollower
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/followers")
    suspend fun getGithubFollower(
        @Path("username") username: String
    ): List<ResponseGitHubFollower>
}