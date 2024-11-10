package com.example.tanorami.core.database.models.hero

import androidx.room.Relation
import com.example.tanorami.core.data.local.entity.BuildDecorationEntity
import com.example.tanorami.core.data.local.entity.BuildRelicEntity
import com.example.tanorami.core.data.local.entity.BuildStatsEquipmentEntity
import com.example.tanorami.core.data.local.entity.BuildWeaponEntity
import com.example.tanorami.core.data.local.models.decoration.DecorationsForBuildDecorations
import com.example.tanorami.core.data.local.models.relic.RelicsForBuildRelics
import com.example.tanorami.core.data.local.models.weapon.WeaponsForBuildWeapons

data class FullBaseBuildHeroEntity(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val weaponsEntity: List<WeaponsForBuildWeapons>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildRelicEntity::class)
    val relicsEntity: List<RelicsForBuildRelics>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildDecorationEntity::class)
    val decorationsEntity: List<DecorationsForBuildDecorations>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildStatsEquipmentEntity::class)
    val buildStatsEquipmentEntity: BuildStatsEquipmentEntity
)