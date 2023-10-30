package com.example.honkaihelper.data.local.models

import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.BuildRelicEntity
import com.example.honkaihelper.data.local.entity.BuildWeaponEntity
import com.example.honkaihelper.data.local.entity.OptimalStatsHeroEntity

data class FullBaseBuildHero(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = OptimalStatsHeroEntity::class)
    val optimalStatsHeroEntity: OptimalStatsHeroEntity,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val buildWeaponEntity: List<BuildWeaponEntity>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val buildRelicEntity: List<BuildRelicEntity>
)