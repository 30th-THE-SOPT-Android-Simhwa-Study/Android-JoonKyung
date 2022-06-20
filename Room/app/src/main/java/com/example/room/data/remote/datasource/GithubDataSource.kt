package com.example.room.data.remote.datasource

import com.example.room.data.remote.github.ResponseGitHubFollower
import com.example.room.data.remote.github.ResponseRepo

interface GithubDataSource {
    suspend fun getGithubFollower(username: String): List<ResponseGitHubFollower>
    suspend fun getGithubRepository(username: String): List<ResponseRepo>

}