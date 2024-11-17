package com.example.data.local.models.decoration

import androidx.room.Relation
import com.example.data.local.entity.DecorationEntity

data class DecorationForBuildRelation(
    val idDecoration: Int,
    val top: Int,

    @Relation(parentColumn = "idDecoration", entityColumn = "idDecoration", entity = DecorationEntity::class)
    val decoration: DecorationEntity
)
