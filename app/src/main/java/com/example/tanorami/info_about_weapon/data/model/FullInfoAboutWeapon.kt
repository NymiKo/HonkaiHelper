package com.example.tanorami.info_about_weapon.data.model

import com.example.core.domain.repository.path.Path
import com.example.core.domain.repository.weapon.Weapon

data class FullInfoAboutWeapon(
    val weapon: Weapon,
    val path: Path
)
