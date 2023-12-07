package com.example.tanorami.data.local.models.weapon

import androidx.room.Relation
import com.example.tanorami.data.local.entity.WeaponEntity

data class WeaponsForBuildWeapons(

    val idWeapon: Int,
    val top: Int,

    @Relation(parentColumn = "idWeapon", entityColumn = "idWeapon", entity = WeaponEntity::class)
    val weapons: WeaponEntity,
)
