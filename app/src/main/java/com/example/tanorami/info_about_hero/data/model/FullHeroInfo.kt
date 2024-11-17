package com.example.tanorami.info_about_hero.data.model

data class FullHeroInfo(
    val heroModel: com.example.domain.repository.hero.model.HeroModel,
    val path: com.example.domain.repository.path.Path,
    val element: com.example.domain.repository.element.Element,
    val abilitiesList: List<com.example.domain.repository.ability.Ability>,
    val eidolonsList: List<com.example.domain.repository.eidolon.Eidolon>
)
