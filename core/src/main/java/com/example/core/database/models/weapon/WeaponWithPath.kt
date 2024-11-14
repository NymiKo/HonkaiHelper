package com.example.core.database.models.weapon

import androidx.room.Embedded
import androidx.room.Relation
import com.example.core.database.entity.PathEntity
import com.example.core.database.entity.WeaponEntity

data class WeaponWithPath(
    @Embedded
    val weapon: WeaponEntity,

    @Relation(parentColumn = "path", entityColumn = "idPath", entity = PathEntity::class)
    val path: PathEntity
)
