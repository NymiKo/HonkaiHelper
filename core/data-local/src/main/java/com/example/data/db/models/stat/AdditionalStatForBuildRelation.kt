package com.example.data.db.models.stat

import androidx.room.Relation
import com.example.data.db.entity.StatEntity

data class AdditionalStatForBuildRelation(
    val idStat: Int,

    @Relation(parentColumn = "idStat", entityColumn = "idStat", entity = StatEntity::class)
    val statEntity: StatEntity,
)
