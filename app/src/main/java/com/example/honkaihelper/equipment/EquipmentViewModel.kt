package com.example.honkaihelper.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.R
import com.example.honkaihelper.createteam.CreateTeamUIState
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.equipment.data.EquipmentRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class EquipmentViewModel @Inject constructor(
    private val repository: EquipmentRepository
): ViewModel() {

    private val _uiState = MutableLiveData<EquipmentUiState<Any>>(EquipmentUiState.LOADING)
    val uiState: LiveData<EquipmentUiState<Any>> = _uiState

    fun getWeapon(path: Int) = viewModelScope.launch {
        _uiState.value = EquipmentUiState.LOADING
        val result = repository.getWeapon(path)
        when(result) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> _uiState.value = EquipmentUiState.SUCCESS(result.data)
        }
    }

    fun getRelic() = viewModelScope.launch {
        _uiState.value = EquipmentUiState.LOADING
        val result = repository.getRelic()
        when(result) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> _uiState.value = EquipmentUiState.SUCCESS(result.data)
        }
    }

    fun getDecoration() = viewModelScope.launch {
        _uiState.value = EquipmentUiState.LOADING
        val result = repository.getDecoration()
        when(result) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> _uiState.value = EquipmentUiState.SUCCESS(result.data)
        }
    }

    private fun errorHandler(errorCode: Int) {
        when(errorCode) {
            105 -> _uiState.value = EquipmentUiState.ERROR(R.string.check_your_internet_connection)
            else -> _uiState.value = EquipmentUiState.ERROR(R.string.unknown_error)
        }
    }

}