package com.example.tanorami.info_about_hero.presentation.models

import com.example.core.base.UiState

data class InfoAboutHeroScreenUiState(
    val heroModel: com.example.domain.repository.hero.model.HeroModel? = null,
    val path: com.example.domain.repository.path.Path? = null,
    val element: com.example.domain.repository.element.Element? = null,
    val abilitiesList: List<com.example.domain.repository.ability.Ability> = emptyList(),
    val eidolonsList: List<com.example.domain.repository.eidolon.Eidolon> = emptyList()
) : UiState
