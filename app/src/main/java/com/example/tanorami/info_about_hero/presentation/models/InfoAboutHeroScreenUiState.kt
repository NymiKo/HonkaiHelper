package com.example.tanorami.info_about_hero.presentation.models

import com.example.core.base.UiState
import com.example.domain.repository.ability.AbilityModel
import com.example.domain.repository.eidolon.EidolonModel
import com.example.domain.repository.element.ElementModel
import com.example.domain.repository.hero.model.HeroModel
import com.example.domain.repository.path.PathModel

data class InfoAboutHeroScreenUiState(
    val heroModel: HeroModel? = null,
    val path: PathModel? = null,
    val element: ElementModel? = null,
    val abilitiesList: List<AbilityModel> = emptyList(),
    val eidolonsList: List<EidolonModel> = emptyList()
) : UiState
