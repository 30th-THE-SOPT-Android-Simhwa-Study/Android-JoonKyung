package com.example.room.presentation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepositoryViewModel : ViewModel() {

    private val _repositoryData = MutableLiveData<List<RepositoryData>>()
    val repositoryData: LiveData<List<RepositoryData>>
        get() = _repositoryData

    fun getRepositoryData() {
        val data = mutableListOf<RepositoryData>()
        for (i in 1..7) {
            data.add(RepositoryData("안드로이드 과제 레포지토리$i", "안드로이드 파트 과제$i"))
        }
        _repositoryData.value = data
    }

}