package com.example.data.source.weapon

import com.example.data.db.entity.WeaponEntity
import com.example.data.db.models.weapon.WeaponForBuildRelation
import com.example.domain.repository.weapon.WeaponForBuildModel
import com.example.domain.repository.weapon.WeaponModel

fun WeaponEntity.toWeaponModel() = WeaponModel(
    idWeapon,
    name,
    story,
    description,
    image,
    path,
    rarity,
)

fun WeaponForBuildRelation.toWeaponForBuildModel() = WeaponForBuildModel(
    idWeapon,
    top,
    weaponEntity.toWeaponModel()
)