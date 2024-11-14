package com.example.tanorami.profile.presentation.models

import com.example.core.base.UiEffect

sealed interface ProfileScreenSideEffects : UiEffect {
    data object OnLoginScreen : ProfileScreenSideEffects
    class OnChangeNicknameScreen(val nickname: String) : ProfileScreenSideEffects
    class OnEditTeamScreen(val idTeam: Long) : ProfileScreenSideEffects
    class OnEditBuildHeroScreen(val idBuild: Long) : ProfileScreenSideEffects
    class ShowToastError(val message: Int) : ProfileScreenSideEffects
}