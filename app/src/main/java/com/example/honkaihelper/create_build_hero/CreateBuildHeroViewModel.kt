package com.example.honkaihelper.create_build_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.create_build_hero.data.CreateBuildHeroRepository
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBuildHeroViewModel @Inject constructor(
    private val repository: CreateBuildHeroRepository
) : ViewModel() {

    private val _hero = MutableLiveData<Hero>()
    val hero: LiveData<Hero> = _hero

    private val _weapon = MutableLiveData<Equipment>()
    val weapon: LiveData<Equipment> = _weapon

    fun getHero(idHero: Int) = viewModelScope.launch {
        _hero.value = repository.getHero(idHero)
    }

    fun addWeapon(equipment: Equipment) {
        _weapon.value = equipment
    }
}