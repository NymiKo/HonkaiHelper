package com.example.tanorami.create_build_heroes_list.presentation.models

import com.example.base.UiState
import com.example.common.HeroBaseInfoModel

data class CreateBuildHeroesListScreenUiState(
    val heroesList: List<HeroBaseInfoModel> = emptyList(),
) : UiState