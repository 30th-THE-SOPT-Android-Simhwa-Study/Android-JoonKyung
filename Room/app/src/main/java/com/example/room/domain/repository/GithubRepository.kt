package com.example.room.domain.repository

import com.example.room.data.remote.github.ResponseGitHubFollower
import com.example.room.data.remote.github.ResponseRepo

interface GithubRepository {

    suspend fun getGithubFollower(
        username: String
    ): Result<List<ResponseGitHubFollower>>

    suspend fun getGithubRepo(
        username: String
    ): Result<List<ResponseRepo>>
}