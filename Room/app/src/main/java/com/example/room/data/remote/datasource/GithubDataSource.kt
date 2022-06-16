package com.example.room.data.remote.datasource

import com.example.room.data.remote.github.ResponseGitHubFollower

interface GithubDataSource {
    suspend fun getGithubFollower(username: String): List<ResponseGitHubFollower>
}