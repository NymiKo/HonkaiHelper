package com.example.tanorami.core.data.local.models.weapon

import androidx.room.Relation
import com.example.tanorami.core.data.local.entity.WeaponEntity

data class WeaponsForBuildWeapons(

    val idWeapon: Int,
    val top: Int,

    @Relation(parentColumn = "idWeapon", entityColumn = "idWeapon", entity = WeaponEntity::class)
    val weapons: WeaponEntity,
)
