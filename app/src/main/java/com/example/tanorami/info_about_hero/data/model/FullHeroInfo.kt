package com.example.tanorami.info_about_hero.data.model

import com.example.domain.repository.ability.AbilityModel
import com.example.domain.repository.eidolon.EidolonModel
import com.example.domain.repository.element.ElementModel
import com.example.domain.repository.hero.model.HeroModel
import com.example.domain.repository.path.PathModel

data class FullHeroInfo(
    val heroModel: HeroModel,
    val path: PathModel,
    val element: ElementModel,
    val abilitiesList: List<AbilityModel>,
    val eidolonsList: List<EidolonModel>
)
