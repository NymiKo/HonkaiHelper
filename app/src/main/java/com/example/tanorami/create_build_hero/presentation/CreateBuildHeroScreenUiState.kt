package com.example.tanorami.create_build_hero.presentation

import com.example.tanorami.R

data class CreateBuildHeroScreenUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.error,
    val isCreateBuild: Boolean  = false,
    val idBuild: Long? = null,
    val idHero: Int? = null,
)
