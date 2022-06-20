package com.example.room.presentation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.domain.repository.GithubRepository
import com.example.room.presentation.follower.FollowerData
import com.example.room.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _repositoryData = MutableLiveData<List<RepositoryData>>()
    val repositoryData: LiveData<List<RepositoryData>>
        get() = _repositoryData

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = _statusMessage

    fun getGithubFollower() {
        val data = mutableListOf<RepositoryData>()

        viewModelScope.launch {
            githubRepository.getGithubRepo(
                "lee989898"
            ).onSuccess {
                for (i in it.indices) {
                    data.add(
                        RepositoryData(
                            it[i].id,
                            it[i].full_name,
                            it[i].name
                        )
                    )
                }
                _repositoryData.value = data
            }.onFailure {
                _statusMessage.postValue(Event("깃허브 팔로우 불러오는데 실패했습니다."))
            }
        }
    }

}