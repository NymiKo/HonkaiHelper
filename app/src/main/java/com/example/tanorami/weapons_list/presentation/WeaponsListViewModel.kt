package com.example.tanorami.weapons_list.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.weapons_list.data.WeaponsListRepository
import com.example.tanorami.weapons_list.presentation.models.WeaponsListScreenEvents
import com.example.tanorami.weapons_list.presentation.models.WeaponsListScreenSideEffects
import com.example.tanorami.weapons_list.presentation.models.WeaponsListScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeaponsListViewModel @Inject constructor(
    private val repository: WeaponsListRepository
) : BaseViewModel<WeaponsListScreenUiState, WeaponsListScreenEvents, WeaponsListScreenSideEffects>(
    initialState = WeaponsListScreenUiState()
) {
    init {
        getWeaponsList()
    }

    override fun onEvent(event: WeaponsListScreenEvents) {
        when (event) {
            is WeaponsListScreenEvents.OnWeaponClick -> sendSideEffect(
                WeaponsListScreenSideEffects.OnInfoAboutWeaponScreen(
                    event.idWeapon
                )
            )

            is WeaponsListScreenEvents.SearchTextChanged -> uiState = uiState.copy(
                searchTextField = uiState.searchTextField.copy(value = event.newValue),
                filteredWeaponsList = uiState.weaponsList.filter { weapon ->
                    weapon.name.lowercase().contains(event.newValue.lowercase())
                }
            )
        }
    }

    private fun getWeaponsList() = viewModelScope.launch {
        uiState = uiState.copy(weaponsList = repository.getWeaponsList())
    }
}