package com.example.data.source.hero.mapper

import com.example.common.HeroBaseInfoModel
import com.example.data.db.entity.HeroEntity
import com.example.data.db.models.hero.HeroBaseInfoProjection
import com.example.data.db.models.hero.HeroFullBaseBuildRelations
import com.example.data.db.models.hero.HeroFullInfoRelations
import com.example.data.source.ability.toAbilityModel
import com.example.data.source.build_stats_equipment.toBuildStatsEquipmentModel
import com.example.data.source.decoration.mapper.toDecorationForBuildModel
import com.example.data.source.eidolon.toEidolonModel
import com.example.data.source.element.toElementModel
import com.example.data.source.path.toPathModel
import com.example.data.source.relic.mapper.toRelicForBuildModel
import com.example.data.source.weapon.mapper.toWeaponForBuildModel
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