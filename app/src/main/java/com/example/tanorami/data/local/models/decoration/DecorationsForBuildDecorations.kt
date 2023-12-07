package com.example.tanorami.data.local.models.decoration

import androidx.room.Relation
import com.example.tanorami.data.local.entity.DecorationEntity

data class DecorationsForBuildDecorations(
    val idDecoration: Int,
    val top: Int,

    @Relation(parentColumn = "idDecoration", entityColumn = "idDecoration", entity = DecorationEntity::class)
    val decoration: DecorationEntity
)
