package com.example.domain.repository.weapon.models

import com.example.domain.repository.path.PathModel

data class WeaponWithPathModel(
    val weapon: WeaponModel,
    val path: PathModel,
)
