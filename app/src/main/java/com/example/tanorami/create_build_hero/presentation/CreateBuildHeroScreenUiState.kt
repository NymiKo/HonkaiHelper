package com.example.tanorami.create_build_hero.presentation

import com.example.tanorami.R
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.create_build_hero.data.model.BuildHeroModel
import com.example.tanorami.heroes.data.model.Hero

data class CreateBuildHeroScreenUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.error,
    val isCreateBuild: Boolean  = false,
    val buildHeroFromUser: BuildHeroModel? = null,
    val idBuild: Long? = null,
    val idHero: Int? = null,
    val hero: Hero? = null,
)
