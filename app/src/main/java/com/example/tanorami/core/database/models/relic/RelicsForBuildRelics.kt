package com.example.tanorami.core.database.models.relic

import androidx.room.Relation
import com.example.tanorami.core.database.entity.RelicEntity

data class RelicsForBuildRelics(
    val idRelic: Int,
    val top: Int,

    @Relation(parentColumn = "idRelic", entityColumn = "idRelic", entity = RelicEntity::class)
    val relic: RelicEntity
)
