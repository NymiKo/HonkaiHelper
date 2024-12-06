package com.example.domain.repository.weapon.models

import com.example.common.WeaponModel

data class WeaponForBuildModel(
    val idWeapon: Int,
    val top: Int,
    val weapon: WeaponModel,
)
