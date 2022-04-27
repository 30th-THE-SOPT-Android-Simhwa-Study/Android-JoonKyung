package com.lee989898.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val count = MutableLiveData(0)

    fun increase(){
        count.value = count.value?.plus(1)
    }

    fun getCount(): LiveData<Int> = count

}