package com.example.tanorami.info_about_hero.data.model

import com.example.tanorami.heroes.data.model.Hero

data class FullHeroInfo(
    val hero: Hero,
    val path: Path,
    val element: Element,
    val abilitiesList: List<Ability>,
    val eidolonsList: List<Eidolon>
)
