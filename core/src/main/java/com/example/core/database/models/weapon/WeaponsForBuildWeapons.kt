package com.example.core.database.models.weapon

import androidx.room.Relation
import com.example.core.database.entity.WeaponEntity

data class WeaponsForBuildWeapons(

    val idWeapon: Int,
    val top: Int,

    @Relation(parentColumn = "idWeapon", entityColumn = "idWeapon", entity = WeaponEntity::class)
    val weapons: WeaponEntity,
)
