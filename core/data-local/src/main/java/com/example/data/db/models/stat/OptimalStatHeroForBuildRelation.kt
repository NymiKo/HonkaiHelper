package com.example.data.db.models.stat

import androidx.room.Relation
import com.example.data.db.entity.StatEntity

data class OptimalStatHeroForBuildRelation(
    val idStat: Int,
    val statValue: String,
    val remark: String?,

    @Relation(parentColumn = "idStat", entityColumn = "idStat", entity = StatEntity::class)
    val statEntity: StatEntity,
)
