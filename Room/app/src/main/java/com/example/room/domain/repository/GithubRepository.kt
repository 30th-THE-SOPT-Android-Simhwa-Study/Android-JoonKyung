package com.example.room.domain.repository

import com.example.room.data.remote.github.ResponseGitHubFollower

interface GithubRepository {

    suspend fun getGithubFollower(
        username: String
    ): Result<List<ResponseGitHubFollower>>
}