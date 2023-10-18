package com.example.honkaihelper.create_build_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.honkaihelper.equipment.data.model.Equipment
import javax.inject.Inject

class CreateBuildHeroViewModel @Inject constructor() : ViewModel() {

    private val _weaponList = MutableLiveData<List<Equipment>>()
    val weaponList: LiveData<List<Equipment>> = _weaponList

    fun addWeapon(equipment: Equipment) {
        val currentList = _weaponList.value ?: emptyList()
        val newList = currentList.toMutableList()
        if (!newList.contains(equipment)) {
            newList.add(equipment)
            _weaponList.value = newList
        }
    }

    fun removeWeapon(idEquipment: Int) {
        val currentList = _weaponList.value ?: emptyList()
        val newList = currentList.toMutableList()
        val position = newList.indexOfFirst { it.id == idEquipment }
        newList.removeAt(position)
        _weaponList.value = newList
    }
}