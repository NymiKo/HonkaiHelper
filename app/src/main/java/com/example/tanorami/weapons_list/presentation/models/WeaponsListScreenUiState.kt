package com.example.tanorami.weapons_list.presentation.models

import com.example.core.base.UiState
import com.example.core.base.models.TextField

data class WeaponsListScreenUiState(
    val searchTextField: TextField = TextField(),
    val weaponsList: List<com.example.domain.repository.weapon.Weapon> = emptyList(),
    val filteredWeaponsList: List<com.example.domain.repository.weapon.Weapon> = emptyList(),
) : UiState
