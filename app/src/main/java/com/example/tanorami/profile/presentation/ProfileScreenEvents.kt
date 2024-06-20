package com.example.tanorami.profile.presentation

import java.io.File

internal sealed interface ProfileScreenEvents {
    data object FetchProfile: ProfileScreenEvents
    data object OnLoginScreen: ProfileScreenEvents
    data object OnChangeNicknameScreen: ProfileScreenEvents
    data class OnEditTeamScreen(val idTeam: Long): ProfileScreenEvents
    data class OnEditBuildHeroScreen(val idBuild: Long): ProfileScreenEvents
    data class UploadAvatarOnServer(val file: File): ProfileScreenEvents
    data object LogoutAccount: ProfileScreenEvents
}