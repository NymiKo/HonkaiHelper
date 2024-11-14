package com.example.tanorami.weapons_list.presentation.models

import com.example.core.base.models.TextField
import com.example.core.base.UiState
import com.example.core.domain.repository.weapon.Weapon

data class WeaponsListScreenUiState(
    val searchTextField: TextField = TextField(),
    val weaponsList: List<Weapon> = emptyList(),
    val filteredWeaponsList: List<Weapon> = emptyList(),
) : UiState
