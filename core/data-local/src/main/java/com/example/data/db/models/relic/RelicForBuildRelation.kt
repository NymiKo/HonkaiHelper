package com.example.data.db.models.relic

import androidx.room.Relation
import com.example.data.db.entity.RelicEntity

data class RelicForBuildRelation(
    val idRelic: Int,
    val top: Int,

    @Relation(parentColumn = "idRelic", entityColumn = "idRelic", entity = RelicEntity::class)
    val relic: RelicEntity
)
