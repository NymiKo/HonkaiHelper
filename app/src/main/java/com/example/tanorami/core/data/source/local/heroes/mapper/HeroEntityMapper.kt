package com.example.tanorami.core.data.source.local.heroes.mapper

import com.example.tanorami.core.database.entity.HeroEntity
import com.example.tanorami.heroes.data.model.Hero

fun HeroEntity.toHero() =
    Hero(id, name, story, localAvatarPath, localSplashArtPath, rarity, idPath, idElement)

fun Hero.toHeroEntity() = HeroEntity(id, name, story, rarity, idPath, idElement, avatar, splashArt)