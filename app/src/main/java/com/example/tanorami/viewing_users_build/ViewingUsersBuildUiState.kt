package com.example.tanorami.viewing_users_build

import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

sealed class ViewingUsersBuildUiState {
    object LOADING: ViewingUsersBuildUiState()
    data class SUCCESS(val fullBuildHeroFromUser: FullBuildHeroFromUser): ViewingUsersBuildUiState()
    data class ERROR(val message: Int): ViewingUsersBuildUiState()
}