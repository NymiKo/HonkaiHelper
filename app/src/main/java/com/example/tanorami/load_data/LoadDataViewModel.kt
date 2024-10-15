package com.example.tanorami.load_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.data.data_store.AppDataStore
import com.example.tanorami.load_data.data.LoadDataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadDataViewModel @Inject constructor(
    private val repository: LoadDataRepository,
    private val dataStore: AppDataStore,
) : ViewModel() {

    private val _dataLoaded = MutableLiveData<LoadDataUiState>()
    val dataLoaded: LiveData<LoadDataUiState> = _dataLoaded

    fun getNewData(versionDB: String?) = viewModelScope.launch {
        _dataLoaded.value = LoadDataUiState.LOADING
        val result = repository.downloadingData()
        when (result) {
            true -> {
                versionDB?.let {
                    dataStore.saveVersionDB(it)
                    _dataLoaded.value = LoadDataUiState.SUCCESS
                }
            }

            false -> {
                _dataLoaded.value = LoadDataUiState.ERROR
            }
        }
    }
}