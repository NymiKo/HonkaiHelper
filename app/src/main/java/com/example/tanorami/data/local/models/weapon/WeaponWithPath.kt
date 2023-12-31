package com.example.tanorami.data.local.models.weapon

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tanorami.data.local.entity.PathEntity
import com.example.tanorami.data.local.entity.WeaponEntity

data class WeaponWithPath(
    @Embedded
    val weapon: WeaponEntity,

    @Relation(parentColumn = "path", entityColumn = "idPath", entity = PathEntity::class)
    val path: PathEntity
)
