package com.example.room.data.remote.datasourceimpl

import com.example.room.data.remote.api.GithubService
import com.example.room.data.remote.datasource.GithubDataSource
import com.example.room.data.remote.github.ResponseGitHubFollower
import com.example.room.data.remote.github.ResponseRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(
    private val githubService: GithubService
): GithubDataSource {

    override suspend fun getGithubFollower(username: String): List<ResponseGitHubFollower> = githubService.getGithubFollower(username)
    override suspend fun getGithubRepository(username: String): Response<List<ResponseRepo>> = githubService.getRepository(username)
}