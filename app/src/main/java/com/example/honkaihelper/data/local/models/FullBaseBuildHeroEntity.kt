package com.example.honkaihelper.data.local.models

import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.BuildDecorationEntity
import com.example.honkaihelper.data.local.entity.BuildRelicEntity
import com.example.honkaihelper.data.local.entity.BuildStatsEquipmentEntity
import com.example.honkaihelper.data.local.entity.BuildWeaponEntity
import com.example.honkaihelper.data.local.entity.OptimalStatsHeroEntity

data class FullBaseBuildHeroEntity(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = OptimalStatsHeroEntity::class)
    val optimalStatsHeroEntity: OptimalStatsHeroEntity,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val weaponsEntity: List<WeaponsForBuildWeapons>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildRelicEntity::class)
    val relicsEntity: List<RelicsForBuildRelics>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildDecorationEntity::class)
    val buildDecorationEntity: List<BuildDecorationEntity>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildStatsEquipmentEntity::class)
    val buildStatsEquipmentEntity: BuildStatsEquipmentEntity
)