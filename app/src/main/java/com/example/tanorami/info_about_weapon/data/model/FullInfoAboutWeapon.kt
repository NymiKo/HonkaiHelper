package com.example.tanorami.info_about_weapon.data.model

import com.example.domain.repository.path.PathModel
import com.example.domain.repository.weapon.models.WeaponModel

data class FullInfoAboutWeapon(
    val weapon: WeaponModel,
    val path: PathModel
)
