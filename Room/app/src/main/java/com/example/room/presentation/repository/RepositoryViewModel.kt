package com.example.room.presentation.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.data.RetrofitInstance
import com.example.room.data.remote.api.GithubService
import com.example.room.data.remote.github.ResponseRepo
import com.example.room.domain.repository.GithubRepository
import com.example.room.presentation.follower.FollowerData
import com.example.room.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    val getRepository: StateFlow<List<ResponseRepo>?> = flow {
        val responseRepository = withContext(Dispatchers.IO) {
            RetrofitInstance.GITHUB_SERVICE.getRepository(
                "lee989898"
            ).body()
        }
        emit(responseRepository)
        Log.d("repo : emit!!", responseRepository.toString())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

//    fun getGithubFollower() {
//        val data = mutableListOf<RepositoryData>()
//
//        viewModelScope.launch {
//            githubRepository.getGithubRepo(
//                "lee989898"
//            ).onSuccess {
//                for (i in it.indices) {
//                    data.add(
//                        RepositoryData(
//                            it[i].id,
//                            it[i].full_name,
//                            it[i].name
//                        )
//                    )
//                }
//                _repositoryData.value = data
//            }.onFailure {
//                _statusMessage.postValue(Event("깃허브 팔로우 불러오는데 실패했습니다."))
//            }
//        }
//    }


}


