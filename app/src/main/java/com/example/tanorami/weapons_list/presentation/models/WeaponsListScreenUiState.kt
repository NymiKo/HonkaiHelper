package com.example.tanorami.weapons_list.presentation.models

import com.example.tanorami.auth.login.presentation.models.TextField
import com.example.tanorami.base.UiState
import com.example.tanorami.weapons_list.domain.models.Weapon

data class WeaponsListScreenUiState(
    val searchTextField: TextField = TextField(),
    val weaponsList: List<Weapon> = emptyList(),
    val filteredWeaponsList: List<Weapon> = emptyList(),
) : UiState
