package com.example.honkaihelper.data.local.models.weapon

import androidx.room.Embedded
import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.PathEntity
import com.example.honkaihelper.data.local.entity.WeaponEntity

data class WeaponWithPath(
    @Embedded
    val weapon: WeaponEntity,

    @Relation(parentColumn = "path", entityColumn = "idPath", entity = PathEntity::class)
    val path: PathEntity
)
