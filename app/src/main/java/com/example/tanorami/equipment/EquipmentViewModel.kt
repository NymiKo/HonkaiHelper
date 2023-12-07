package com.example.tanorami.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.equipment.data.EquipmentRepository
import com.example.tanorami.equipment.data.model.Equipment
import kotlinx.coroutines.launch
import javax.inject.Inject

class EquipmentViewModel @Inject constructor(
    private val repository: EquipmentRepository
): ViewModel() {

    private val _equipmentList = MutableLiveData<List<Equipment>>()
    val equipmentList: LiveData<List<Equipment>> = _equipmentList

    fun getWeapons(path: Int) = viewModelScope.launch {
        _equipmentList.value = repository.getWeapons(path)
    }

    fun getRelics() = viewModelScope.launch {
        _equipmentList.value = repository.getRelics()
    }

    fun getDecorations() = viewModelScope.launch {
        _equipmentList.value = repository.getDecorations()
    }
}