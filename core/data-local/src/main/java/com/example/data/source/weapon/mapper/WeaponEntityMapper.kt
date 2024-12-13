package com.example.data.source.weapon.mapper

import com.example.common.WeaponModel
import com.example.data.db.entity.WeaponEntity
import com.example.data.db.models.weapon.WeaponForBuildRelation
import com.example.data.db.models.weapon.WeaponWithHeroesRelation
import com.example.data.db.models.weapon.WeaponWithPathRelation
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.data.source.path.toPathModel
import com.example.domain.repository.weapon.models.WeaponForBuildModel
import com.example.domain.repository.weapon.models.WeaponWithHeroesModel
import com.example.domain.repository.weapon.models.WeaponWithPathModel

fun WeaponEntity.toWeaponModel() = WeaponModel(
    idWeapon,
    name,
    story,
    description,
    image,
    path,
    rarity,
)

fun WeaponModel.toWeaponEntity() = WeaponEntity(
    idWeapon,
    name,
    story,
    description,
    image,
    path,
    rarity,
)

fun WeaponForBuildRelation.toWeaponForBuildModel() = WeaponForBuildModel(
    idWeapon,
    top,
    weaponEntity.toWeaponModel()
)

fun WeaponWithPathRelation.toWeaponWithPathModel() = WeaponWithPathModel(
    weapon = weapon.toWeaponModel(),
    path = path.toPathModel()
)

fun WeaponWithHeroesRelation.toWeaponWithHeroesModel() = WeaponWithHeroesModel(
    weapon = weapon.toWeaponModel(),
    path = path.toPathModel(),
    heroesList = heroesList.map { it.hero.toHeroBaseInfoModel() }
)