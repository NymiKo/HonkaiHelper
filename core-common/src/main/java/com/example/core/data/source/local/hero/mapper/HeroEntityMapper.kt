package com.example.core.data.source.local.hero.mapper

import com.example.core.data.source.local.ability.toAbilityModel
import com.example.core.data.source.local.build_stats_equipment.toBuildStatsEquipmentModel
import com.example.core.data.source.local.decoration.toDecorationForBuildModel
import com.example.core.data.source.local.eidolon.toEidolonModel
import com.example.core.data.source.local.element.toElementModel
import com.example.core.data.source.local.path.toPathModel
import com.example.core.data.source.local.relic.toRelicForBuildModel
import com.example.core.data.source.local.weapon.toWeaponForBuildModel
import com.example.data.local.entity.HeroEntity
import com.example.data.local.models.hero.HeroBaseInfoProjection
import com.example.data.local.models.hero.HeroFullBaseBuildRelations
import com.example.data.local.models.hero.HeroFullInfoRelations
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.domain.repository.hero.model.HeroFullBaseBuildModel
import com.example.domain.repository.hero.model.HeroFullInfoModel
import com.example.domain.repository.hero.model.HeroModel


fun HeroEntity.toHeroModel() = HeroModel(
    id, name, story, localAvatarPath, localSplashArtPath, rarity, idPath, idElement
)

fun HeroModel.toHeroEntity() = HeroEntity(
    id,
    name,
    story,
    rarity,
    idPath,
    idElement,
    avatar,
    splashArt
)

fun HeroBaseInfoProjection.toHeroBaseInfoModel() =
    HeroBaseInfoModel(id, name, localAvatarPath, rarity)

fun HeroFullInfoRelations.toHeroFullInfoModel() = HeroFullInfoModel(
    hero = heroEntity.toHeroModel(),
    pathModelHero = pathEntity.toPathModel(),
    elementModelHero = elementEntity.toElementModel(),
    abilityModelHeroes = abilityEntity.map { it.toAbilityModel() },
    eidolonModelHeroes = eidolonEntity.map { it.toEidolonModel() },
)

fun HeroFullBaseBuildRelations.toHeroFullBaseBuildModel() = HeroFullBaseBuildModel(
    id,
    weapons = weaponsForBuild.map { it.toWeaponForBuildModel() },
    relics = relicsForBuild.map { it.toRelicForBuildModel() },
    decorations = decorationsForBuild.map { it.toDecorationForBuildModel() },
    statsEquipment = buildStatsEquipmentEntity.toBuildStatsEquipmentModel(),
)