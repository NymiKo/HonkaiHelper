package com.example.weapons_list.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField

internal data class WeaponsListScreenUiState(
    val searchTextField: TextField = TextField(),
    val weaponsList: List<com.example.common.WeaponModel> = emptyList(),
    val filteredWeaponsList: List<com.example.common.WeaponModel> = emptyList(),
) : UiState
