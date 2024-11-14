package com.example.tanorami.info_about_hero.data.model

import com.example.core.domain.repository.ability.Ability
import com.example.core.domain.repository.eidolon.Eidolon
import com.example.core.domain.repository.element.Element
import com.example.core.domain.repository.hero.model.HeroModel
import com.example.core.domain.repository.path.Path

data class FullHeroInfo(
    val heroModel: HeroModel,
    val path: Path,
    val element: Element,
    val abilitiesList: List<Ability>,
    val eidolonsList: List<Eidolon>
)
