package com.example.tanorami.info_about_hero.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.info_about_hero.data.model.Ability
import com.example.tanorami.info_about_hero.data.model.Eidolon
import com.example.tanorami.info_about_hero.data.model.Element
import com.example.tanorami.info_about_hero.data.model.Path

data class InfoAboutHeroScreenUiState(
    val hero: Hero? = null,
    val path: Path? = null,
    val element: Element? = null,
    val abilitiesList: List<Ability> = emptyList(),
    val eidolonsList: List<Eidolon> = emptyList()
) : UiState
