package com.example.core.data.source.local.heroes.mapper

import com.example.core.database.entity.HeroEntity
import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.domain.repository.hero.model.HeroBaseInfoModel
import com.example.core.domain.repository.hero.model.HeroModel


fun HeroEntity.toHeroModel() =
    HeroModel(id, name, story, localAvatarPath, localSplashArtPath, rarity, idPath, idElement)

fun HeroModel.toHeroEntity() =
    HeroEntity(id, name, story, rarity, idPath, idElement, avatar, splashArt)

fun HeroBaseInfoProjection.toHeroBaseInfoModel() =
    HeroBaseInfoModel(id, name, localAvatarPath, rarity)