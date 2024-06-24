package com.example.tanorami.change_nickname.presentation

sealed interface ChangeNicknameScreenEvents {
    data class EnteringNickname(val nickname: String): ChangeNicknameScreenEvents
    data object ChangeNickname: ChangeNicknameScreenEvents
    data object OnBack: ChangeNicknameScreenEvents
}