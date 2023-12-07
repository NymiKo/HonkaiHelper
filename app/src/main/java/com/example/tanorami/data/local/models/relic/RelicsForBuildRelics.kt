package com.example.tanorami.data.local.models.relic

import androidx.room.Relation
import com.example.tanorami.data.local.entity.RelicEntity

data class RelicsForBuildRelics(
    val idRelic: Int,
    val top: Int,

    @Relation(parentColumn = "idRelic", entityColumn = "idRelic", entity = RelicEntity::class)
    val relic: RelicEntity
)
