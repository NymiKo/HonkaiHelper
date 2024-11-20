package com.example.tanorami.profile.presentation.models

sealed interface ProfileScreenSideEffects : com.example.base.UiEffect {
    data object OnLoginScreen : ProfileScreenSideEffects
    class OnChangeNicknameScreen(val nickname: String) : ProfileScreenSideEffects
    class OnEditTeamScreen(val idTeam: Long) : ProfileScreenSideEffects
    class OnEditBuildHeroScreen(val idBuild: Long) : ProfileScreenSideEffects
    class ShowToastError(val message: Int) : ProfileScreenSideEffects
}