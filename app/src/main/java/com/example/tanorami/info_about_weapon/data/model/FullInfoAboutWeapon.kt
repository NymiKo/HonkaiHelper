package com.example.tanorami.info_about_weapon.data.model

import com.example.domain.repository.path.PathModel

data class FullInfoAboutWeapon(
    val weapon: com.example.common.WeaponModel,
    val path: PathModel,
)
