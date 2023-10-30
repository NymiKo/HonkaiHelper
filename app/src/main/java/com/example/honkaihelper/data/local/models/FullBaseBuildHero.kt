package com.example.honkaihelper.data.local.models

import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.OptimalStatsHeroEntity

data class FullBaseBuildHero(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = OptimalStatsHeroEntity::class)
    val optimalStatsHeroEntity: OptimalStatsHeroEntity
)