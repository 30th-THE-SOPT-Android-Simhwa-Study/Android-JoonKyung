package com.example.room.data.remote.repository

import androidx.lifecycle.liveData
import com.example.room.data.remote.datasource.GithubDataSource
import com.example.room.data.remote.github.ResponseGitHubFollower
import com.example.room.data.remote.github.ResponseRepo
import com.example.room.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubDataSource: GithubDataSource
) : GithubRepository {

    override suspend fun getGithubFollower(username: String): Result<List<ResponseGitHubFollower>> =
        kotlin.runCatching {
            githubDataSource.getGithubFollower(username)
        }

    override suspend fun getGithubRepo(username: String): Response<List<ResponseRepo>> =
        githubDataSource.getGithubRepository(username)

}