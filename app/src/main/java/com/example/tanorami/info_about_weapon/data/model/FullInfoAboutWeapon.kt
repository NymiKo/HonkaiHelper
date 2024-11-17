package com.example.tanorami.info_about_weapon.data.model

data class FullInfoAboutWeapon(
    val weapon: com.example.domain.repository.weapon.Weapon,
    val path: com.example.domain.repository.path.Path
)
