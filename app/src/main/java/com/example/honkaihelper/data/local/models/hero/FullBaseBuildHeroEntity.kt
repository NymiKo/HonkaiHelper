package com.example.honkaihelper.data.local.models.hero

import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.BuildDecorationEntity
import com.example.honkaihelper.data.local.entity.BuildRelicEntity
import com.example.honkaihelper.data.local.entity.BuildStatsEquipmentEntity
import com.example.honkaihelper.data.local.entity.BuildWeaponEntity
import com.example.honkaihelper.data.local.entity.OptimalStatsHeroEntity
import com.example.honkaihelper.data.local.models.decoration.DecorationsForBuildDecorations
import com.example.honkaihelper.data.local.models.relic.RelicsForBuildRelics
import com.example.honkaihelper.data.local.models.weapon.WeaponsForBuildWeapons

data class FullBaseBuildHeroEntity(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = OptimalStatsHeroEntity::class)
    val optimalStatsHeroEntity: OptimalStatsHeroEntity,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val weaponsEntity: List<WeaponsForBuildWeapons>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildRelicEntity::class)
    val relicsEntity: List<RelicsForBuildRelics>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildDecorationEntity::class)
    val decorationsEntity: List<DecorationsForBuildDecorations>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildStatsEquipmentEntity::class)
    val buildStatsEquipmentEntity: BuildStatsEquipmentEntity
)