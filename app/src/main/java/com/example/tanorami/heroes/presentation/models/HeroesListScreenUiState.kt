package com.example.tanorami.heroes.presentation.models

import com.example.tanorami.auth.login.presentation.models.TextField
import com.example.tanorami.base.UiState
import com.example.tanorami.heroes.data.model.Hero

data class HeroesListScreenUiState(
    val userAvatar: String = "",
    val heroesList: List<Hero> = emptyList(),
    val filteredHeroesList: List<Hero> = emptyList(),
    val search: Search = Search.HERO,
    val searchBarFocus: Boolean = false,
    val searchTextField: TextField = TextField(),
) : UiState
