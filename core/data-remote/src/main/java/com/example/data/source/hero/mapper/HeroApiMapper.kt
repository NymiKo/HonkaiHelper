package com.example.data.source.hero.mapper

import com.example.data.remote.api.hero.model.HeroDto
import com.example.domain.repository.hero.model.HeroModel

fun HeroDto.toHeroModel() = HeroModel(
    id, name, story, avatar, splashArt, rarity, idPath, idElement
)