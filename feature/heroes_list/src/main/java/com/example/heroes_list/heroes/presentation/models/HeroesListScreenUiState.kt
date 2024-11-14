package com.example.heroes_list.heroes.presentation.models

import com.example.core.base.UiState
import com.example.core.base.models.TextField
import com.example.core.domain.repository.hero.model.HeroBaseInfoModel

data class HeroesListScreenUiState(
    val userAvatar: String = "",
    val heroesList: List<HeroBaseInfoModel> = emptyList(),
    val filteredHeroesList: List<HeroBaseInfoModel> = emptyList(),
    val searchBarFocus: Boolean = false,
    val searchTextField: TextField = TextField(),
) : UiState
