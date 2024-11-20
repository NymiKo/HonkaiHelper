package com.example.tanorami.weapons_list.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField
import com.example.domain.repository.weapon.WeaponModel

data class WeaponsListScreenUiState(
    val searchTextField: TextField = TextField(),
    val weaponsList: List<WeaponModel> = emptyList(),
    val filteredWeaponsList: List<WeaponModel> = emptyList(),
) : UiState
