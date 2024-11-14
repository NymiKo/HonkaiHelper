package com.example.tanorami.create_build_heroes_list.presentation.models

import com.example.core.base.UiState
import com.example.core.domain.repository.hero.model.HeroBaseInfoModel

data class CreateBuildHeroesListScreenUiState(
    val heroesList: List<HeroBaseInfoModel> = emptyList()
) : UiState