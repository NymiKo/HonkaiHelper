package com.example.tanorami.create_build_heroes_list.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.heroes.data.model.Hero

data class CreateBuildHeroesListScreenUiState(
    val heroesList: List<Hero> = emptyList()
) : UiState