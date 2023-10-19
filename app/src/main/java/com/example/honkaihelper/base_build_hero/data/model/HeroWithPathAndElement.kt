package com.example.honkaihelper.base_build_hero.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.honkaihelper.data.local.entity.ElementEntity
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.data.local.entity.PathEntity

data class HeroWithPathAndElement(
    @Embedded
    val heroEntity: HeroEntity,

    @Relation(parentColumn = "idPath", entityColumn = "idPath")
    val pathEntity: PathEntity,

    @Relation(parentColumn = "idElement", entityColumn = "idElement")
    val elementEntity: ElementEntity
)
