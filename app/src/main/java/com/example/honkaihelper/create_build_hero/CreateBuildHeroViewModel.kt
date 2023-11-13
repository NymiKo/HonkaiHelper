package com.example.honkaihelper.create_build_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.create_build_hero.data.CreateBuildHeroRepository
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.equipment.data.model.Equipment
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBuildHeroViewModel @Inject constructor(
    private val repository: CreateBuildHeroRepository
) : ViewModel() {

    private val _hero = MutableLiveData<HeroWithNameAvatarRarity>()
    val hero: LiveData<HeroWithNameAvatarRarity> = _hero

    private val _weaponList = MutableLiveData<List<Equipment>>()
    val weaponList: LiveData<List<Equipment>> = _weaponList

    fun getHero(idHero: Int) = viewModelScope.launch {
        _hero.value = repository.getHero(idHero)
    }

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