package com.example.data.db.models.hero

import androidx.room.Relation
import com.example.data.db.entity.HeroEntity

data class HeroBaseInfoRelation(
    val idHero: Int,
    val top: Int,

    @Relation(parentColumn = "idHero", entityColumn = "id", entity = HeroEntity::class)
    val hero: HeroBaseInfoProjection,
)
