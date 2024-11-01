package com.example.tanorami.info_about_weapon.data.model

import com.example.tanorami.info_about_hero.data.model.Path
import com.example.tanorami.weapons_list.domain.models.Weapon

data class FullInfoAboutWeapon(
    val weapon: Weapon,
    val path: Path
)
