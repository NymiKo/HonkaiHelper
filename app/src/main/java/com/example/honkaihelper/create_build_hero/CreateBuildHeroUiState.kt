package com.example.honkaihelper.create_build_hero

sealed class CreateBuildHeroUiState() {
    object CREATION : CreateBuildHeroUiState()
    data class ERROR(val errorMessage: Int): CreateBuildHeroUiState()
    object SENDING_BUILD : CreateBuildHeroUiState()
    object SUCCESS : CreateBuildHeroUiState()
    object SUCCESS_DELETION_BUILD : CreateBuildHeroUiState()
}