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

    private val _dataLoaded = MutableLiveData<LoadDataUiState>()
    val dataLoaded: LiveData<LoadDataUiState> = _dataLoaded

    init {
        getNewData()
    }

    fun getNewData() = viewModelScope.launch {
        _dataLoaded.value = LoadDataUiState.LOADING
        val result = repository.downloadingData()
        when(result) {
            true -> {
                _dataLoaded.value = LoadDataUiState.SUCCESS
            }
            false -> {
                _dataLoaded.value = LoadDataUiState.ERROR
            }
        }
    }
}