package com.example.tanorami.info_about_weapon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.info_about_weapon.data.WeaponInfoRepository
import com.example.tanorami.info_about_weapon.data.model.FullInfoAboutWeapon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutWeaponViewModel @Inject constructor(
    private val repository: WeaponInfoRepository
): ViewModel() {

    private val _weapon = MutableStateFlow<FullInfoAboutWeapon?>(null)
    val weapon: StateFlow<FullInfoAboutWeapon?> = _weapon

    fun getWeapon(idWeapon: Int) = viewModelScope.launch {
        _weapon.emit(repository.getWeapon(idWeapon))
    }
}