package com.example.tanorami.create_build_hero.presentation

sealed class CreateBuildHeroUiState() {
    object CREATION : CreateBuildHeroUiState()
    data class ERROR(val errorMessage: Int): CreateBuildHeroUiState()
    object SENDING_BUILD : CreateBuildHeroUiState()
    object SUCCESS : CreateBuildHeroUiState()
    object SUCCESS_DELETION_BUILD : CreateBuildHeroUiState()
}