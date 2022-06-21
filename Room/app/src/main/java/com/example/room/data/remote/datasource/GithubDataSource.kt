package com.example.room.data.remote.datasource

import com.example.room.data.remote.github.ResponseGitHubFollower
import com.example.room.data.remote.github.ResponseRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GithubDataSource {
    suspend fun getGithubFollower(username: String): List<ResponseGitHubFollower>
    suspend fun getGithubRepository(username: String): Response<List<ResponseRepo>>

}