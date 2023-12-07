package com.example.tanorami.info_about_hero.data.model

import com.example.tanorami.heroes.data.model.Hero

data class FullHeroInfo(
    val hero: Hero,
    val path: Path,
    val element: Element,
    val ability: List<Ability>,
    val eidolon: List<Eidolon>
)
