package com.example.honkaihelper.data.local.models

import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.DecorationEntity

data class DecorationsForBuildDecorations(
    val idDecoration: Int,
    val top: Int,

    @Relation(parentColumn = "idDecoration", entityColumn = "idDecoration", entity = DecorationEntity::class)
    val decoration: DecorationEntity
)
