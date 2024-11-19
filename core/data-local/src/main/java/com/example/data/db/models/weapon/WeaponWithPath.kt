package com.example.data.db.models.weapon

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.db.entity.PathEntity
import com.example.data.db.entity.WeaponEntity

data class WeaponWithPath(
    @Embedded
    val weapon: WeaponEntity,

    @Relation(parentColumn = "path", entityColumn = "idPath", entity = PathEntity::class)
    val path: PathEntity
)
