package com.example.data.local.models.weapon

import androidx.room.Relation
import com.example.data.local.entity.WeaponEntity

data class WeaponForBuildRelation(

    val idWeapon: Int,
    val top: Int,

    @Relation(parentColumn = "idWeapon", entityColumn = "idWeapon", entity = WeaponEntity::class)
    val weaponEntity: WeaponEntity,
)
