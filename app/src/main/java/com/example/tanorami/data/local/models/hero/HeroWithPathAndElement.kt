package com.example.tanorami.data.local.models.hero

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tanorami.data.local.entity.AbilityEntity
import com.example.tanorami.data.local.entity.EidolonEntity
import com.example.tanorami.data.local.entity.ElementEntity
import com.example.tanorami.data.local.entity.HeroEntity
import com.example.tanorami.data.local.entity.PathEntity

data class HeroWithPathAndElement(
    @Embedded
    val heroEntity: HeroEntity,

    @Relation(parentColumn = "idPath", entityColumn = "idPath", entity = PathEntity::class)
    val pathEntity: PathEntity,

    @Relation(parentColumn = "idElement", entityColumn = "idElement", entity = ElementEntity::class)
    val elementEntity: ElementEntity,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = AbilityEntity::class)
    val abilityEntity: List<AbilityEntity>,

    @Relation(parentColumn = "id", entityColumn = "idHero", entity = EidolonEntity::class)
    val eidolonEntity: List<EidolonEntity>
)
