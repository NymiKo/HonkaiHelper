package com.example.honkaihelper.data.local.models.weapon

import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.WeaponEntity

data class WeaponsForBuildWeapons(

    val idWeapon: Int,
    val top: Int,

    @Relation(parentColumn = "idWeapon", entityColumn = "idWeapon", entity = WeaponEntity::class)
    val weapons: WeaponEntity,
)
