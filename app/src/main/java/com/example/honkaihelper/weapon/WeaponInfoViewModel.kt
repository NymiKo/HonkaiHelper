package com.example.honkaihelper.weapon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.weapon.data.WeaponInfoRepository
import com.example.honkaihelper.weapon.data.model.FullWeaponInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeaponInfoViewModel @Inject constructor(
    private val repository: WeaponInfoRepository
): ViewModel() {

    private val _weapon = MutableLiveData<FullWeaponInfo>()
    val weapon: LiveData<FullWeaponInfo> = _weapon

    fun getWeapon(idWeapon: Int) = viewModelScope.launch {
        _weapon.value = repository.getWeapon(idWeapon)
    }
}