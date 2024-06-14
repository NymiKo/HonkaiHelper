package com.example.tanorami.profile.presentation

import java.io.File

internal sealed interface ProfileScreenEvents {
    object FetchProfile: ProfileScreenEvents
    object OnLoginScreen: ProfileScreenEvents
    object OnChangeNicknameScreen: ProfileScreenEvents
    data class OnEditTeamScreen(val idTeam: Long): ProfileScreenEvents
    data class OnEditBuildHeroScreen(val idBuild: Long): ProfileScreenEvents
    object ChangeAvatar: ProfileScreenEvents
    data class LoadAvatar(val file: File): ProfileScreenEvents
    object LogoutAccount: ProfileScreenEvents
}