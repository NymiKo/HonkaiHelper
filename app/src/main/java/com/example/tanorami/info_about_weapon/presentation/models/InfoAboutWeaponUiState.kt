package com.example.tanorami.info_about_weapon.presentation.models

import com.example.base.UiState
import com.example.domain.repository.weapon.models.WeaponWithHeroesModel

data class InfoAboutWeaponUiState(
    val weaponInfo: WeaponWithHeroesModel? = null,
) : UiState
