package com.example.data.db.models.weapon

import androidx.room.Relation
import com.example.data.db.entity.WeaponEntity

data class WeaponForBuildRelation(

    val idWeapon: Int,
    val top: Int,
    val tier: Int,

    @Relation(parentColumn = "idWeapon", entityColumn = "idWeapon", entity = WeaponEntity::class)
    val weaponEntity: WeaponEntity,
)
