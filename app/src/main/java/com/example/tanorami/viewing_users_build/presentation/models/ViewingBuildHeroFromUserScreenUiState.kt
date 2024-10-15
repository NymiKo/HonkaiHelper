package com.example.tanorami.viewing_users_build.presentation.models

import com.example.tanorami.R
import com.example.tanorami.base.UiState
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

data class ViewingBuildHeroFromUserScreenUiState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.error,
    val buildHero: FullBuildHeroFromUser = FullBuildHeroFromUser()
) : UiState