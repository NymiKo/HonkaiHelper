package com.example.tanorami.info_about_hero.presentation.models

import com.example.core.base.UiState
import com.example.core.domain.repository.ability.Ability
import com.example.core.domain.repository.eidolon.Eidolon
import com.example.core.domain.repository.element.Element
import com.example.core.domain.repository.hero.model.HeroModel
import com.example.core.domain.repository.path.Path

data class InfoAboutHeroScreenUiState(
    val heroModel: HeroModel? = null,
    val path: Path? = null,
    val element: Element? = null,
    val abilitiesList: List<Ability> = emptyList(),
    val eidolonsList: List<Eidolon> = emptyList()
) : UiState
