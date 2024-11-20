package com.example.heroes_list.heroes.presentation.models

import com.example.domain.repository.hero.model.HeroBaseInfoModel

data class HeroesListScreenUiState(
    val userAvatar: String = "",
    val heroesList: List<HeroBaseInfoModel> = emptyList(),
    val filteredHeroesList: List<HeroBaseInfoModel> = emptyList(),
    val searchBarFocus: Boolean = false,
    val searchTextField: com.example.base.models.TextField = com.example.base.models.TextField(),
) : com.example.base.UiState
