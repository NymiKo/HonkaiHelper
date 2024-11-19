package com.example.data.db.models.decoration

import androidx.room.Relation
import com.example.data.db.entity.DecorationEntity

data class DecorationForBuildRelation(
    val idDecoration: Int,
    val top: Int,

    @Relation(parentColumn = "idDecoration", entityColumn = "idDecoration", entity = DecorationEntity::class)
    val decoration: DecorationEntity
)
