package com.example.tanorami.info_about_weapon.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.domain.repository.weapon.models.WeaponWithHeroesModel
import com.example.tanorami.info_about_weapon.data.InfoAboutWeaponRepository
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponEvents
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponUiSideEffects
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponUiSideEffects.*
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutWeaponViewModel @Inject constructor(
    private val repository: InfoAboutWeaponRepository,
) : BaseViewModel<InfoAboutWeaponUiState, InfoAboutWeaponEvents, InfoAboutWeaponUiSideEffects>(
    initialState = InfoAboutWeaponUiState()
) {

    private val _weapon = MutableStateFlow<WeaponWithHeroesModel?>(null)
    val weapon: StateFlow<WeaponWithHeroesModel?> = _weapon

    override fun onEvent(event: InfoAboutWeaponEvents) {
        when (event) {
            is InfoAboutWeaponEvents.GetWeaponByID -> getWeapon(event.idWeapon)
            is InfoAboutWeaponEvents.OnHeroClick -> sendSideEffect(
                OnInfoAboutHeroScreen(event.idHero)
            )

            InfoAboutWeaponEvents.OnBackClick -> sendSideEffect(OnBackClick)
        }
    }

    private fun getWeapon(idWeapon: Int) = viewModelScope.launch {
        uiState = uiState.copy(weaponInfo = repository.getWeapon(idWeapon))
    }
}