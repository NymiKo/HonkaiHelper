package com.example.tanorami.core.database.models.hero

import androidx.room.Relation
import com.example.tanorami.core.database.entity.BuildDecorationEntity
import com.example.tanorami.core.database.entity.BuildRelicEntity
import com.example.tanorami.core.database.entity.BuildStatsEquipmentEntity
import com.example.tanorami.core.database.entity.BuildWeaponEntity
import com.example.tanorami.core.database.models.decoration.DecorationsForBuildDecorations
import com.example.tanorami.core.database.models.relic.RelicsForBuildRelics
import com.example.tanorami.core.database.models.weapon.WeaponsForBuildWeapons

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