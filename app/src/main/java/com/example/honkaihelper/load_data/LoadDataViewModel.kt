package com.example.honkaihelper.load_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.load_data.data.LoadDataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadDataViewModel @Inject constructor(
    private val repository: LoadDataRepository
): ViewModel() {

    private val _dataLoaded = MutableLiveData<Boolean>(false)
    val dataLoaded: LiveData<Boolean> = _dataLoaded

    init {
        getNewData()
    }

    fun getNewData() = viewModelScope.launch {
        _dataLoaded.value = repository.getHeroesList()
    }
}