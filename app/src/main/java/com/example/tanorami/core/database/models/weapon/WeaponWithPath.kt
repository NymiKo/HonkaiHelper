package com.example.tanorami.core.database.models.weapon

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tanorami.core.database.entity.PathEntity
import com.example.tanorami.core.database.entity.WeaponEntity

data class WeaponWithPath(
    @Embedded
    val weapon: WeaponEntity,

    @Relation(parentColumn = "path", entityColumn = "idPath", entity = PathEntity::class)
    val path: PathEntity
)
