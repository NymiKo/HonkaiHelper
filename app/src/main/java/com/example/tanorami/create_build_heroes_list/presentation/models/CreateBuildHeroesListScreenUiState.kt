package com.example.tanorami.create_build_heroes_list.presentation.models

import com.example.base.UiState

data class CreateBuildHeroesListScreenUiState(
    val heroesList: List<com.example.domain.repository.hero.model.HeroBaseInfoModel> = emptyList()
) : UiState