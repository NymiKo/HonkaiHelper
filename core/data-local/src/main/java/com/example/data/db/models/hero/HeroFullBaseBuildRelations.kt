package com.example.data.db.models.hero

import androidx.room.Relation
import com.example.data.db.entity.AdditionalStatEntity
import com.example.data.db.entity.BuildDecorationEntity
import com.example.data.db.entity.BuildRelicEntity
import com.example.data.db.entity.BuildStatsEquipmentEntity
import com.example.data.db.entity.BuildWeaponEntity
import com.example.data.db.entity.OptimalStatHeroEntity
import com.example.data.db.models.decoration.DecorationForBuildRelation
import com.example.data.db.models.relic.RelicForBuildRelation
import com.example.data.db.models.stat.AdditionalStatForBuildRelation
import com.example.data.db.models.stat.OptimalStatHeroForBuildRelation
import com.example.data.db.models.weapon.WeaponForBuildRelation

data class HeroFullBaseBuildRelations(

    val id: Int,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildWeaponEntity::class)
    val weaponsForBuild: List<WeaponForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildRelicEntity::class)
    val relicsForBuild: List<RelicForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildDecorationEntity::class)
    val decorationsForBuild: List<DecorationForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = BuildStatsEquipmentEntity::class)
    val buildStatsEquipmentEntity: BuildStatsEquipmentEntity,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = AdditionalStatEntity::class)
    val buildAdditionalStatsEntity: List<AdditionalStatForBuildRelation>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = OptimalStatHeroEntity::class)
    val buildOptimalStatsHeroEntity: List<OptimalStatHeroForBuildRelation>,
)