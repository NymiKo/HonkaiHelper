package com.example.data.local.models.hero

import androidx.room.Relation
import com.example.data.local.entity.BuildDecorationEntity
import com.example.data.local.entity.BuildRelicEntity
import com.example.data.local.entity.BuildStatsEquipmentEntity
import com.example.data.local.entity.BuildWeaponEntity
import com.example.data.local.models.decoration.DecorationForBuildRelation
import com.example.data.local.models.relic.RelicForBuildRelation
import com.example.data.local.models.weapon.WeaponForBuildRelation

data class HeroFullBaseBuildRelations(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val weaponsForBuild: List<WeaponForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildRelicEntity::class)
    val relicsForBuild: List<RelicForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildDecorationEntity::class)
    val decorationsForBuild: List<DecorationForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildStatsEquipmentEntity::class)
    val buildStatsEquipmentEntity: BuildStatsEquipmentEntity
)