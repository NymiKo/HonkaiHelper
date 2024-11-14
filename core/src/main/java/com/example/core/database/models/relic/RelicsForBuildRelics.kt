package com.example.core.database.models.relic

import androidx.room.Relation
import com.example.core.database.entity.RelicEntity

data class RelicsForBuildRelics(
    val idRelic: Int,
    val top: Int,

    @Relation(parentColumn = "idRelic", entityColumn = "idRelic", entity = RelicEntity::class)
    val relic: RelicEntity
)
