package com.example.domain.repository.hero.model

import com.example.domain.repository.ability.AbilityModel
import com.example.domain.repository.eidolon.EidolonModel
import com.example.domain.repository.element.ElementModel
import com.example.domain.repository.path.PathModel

data class HeroFullInfoModel(
    val hero: HeroModel,
    val pathModelHero: PathModel,
    val elementModelHero: ElementModel,
    val abilityModelHeroes: List<AbilityModel>,
    val eidolonModelHeroes: List<EidolonModel>,
)
