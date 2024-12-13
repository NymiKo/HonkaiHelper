package com.example.data.db.models.weapon

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.db.entity.BuildWeaponEntity
import com.example.data.db.entity.PathEntity
import com.example.data.db.entity.WeaponEntity
import com.example.data.db.models.hero.HeroBaseInfoRelation

data class WeaponWithHeroesRelation(
    @Embedded
    val weapon: WeaponEntity,

    @Relation(parentColumn = "path", entityColumn = "idPath", entity = PathEntity::class)
    val path: PathEntity,

    @Relation(
        parentColumn = "idWeapon",
        entityColumn = "idWeapon",
        entity = BuildWeaponEntity::class
    )
    val heroesList: List<HeroBaseInfoRelation>,
)
