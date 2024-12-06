package com.example.heroes_list.heroes.presentation.models

import com.example.base.UiState
import com.example.base.models.TextField
import com.example.common.HeroBaseInfoModel

internal data class HeroesListScreenUiState(
    val userAvatar: String = "",
    val heroesList: List<HeroBaseInfoModel> = emptyList(),
    val filteredHeroesList: List<HeroBaseInfoModel> = emptyList(),
    val searchBarFocus: Boolean = false,
    val searchTextField: TextField = TextField(),
) : UiState
