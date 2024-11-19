package com.example.data.db.models.hero

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.db.entity.AbilityEntity
import com.example.data.db.entity.EidolonEntity
import com.example.data.db.entity.ElementEntity
import com.example.data.db.entity.HeroEntity
import com.example.data.db.entity.PathEntity

data class HeroFullInfoRelations(
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
