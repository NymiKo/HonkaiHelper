package com.example.tanorami.change_nickname.presentation.models

sealed interface ChangeNicknameScreenEvents {
    data class NicknameChanged(val newValue: String) : ChangeNicknameScreenEvents
    data class SetOldNickname(val oldNickname: String) : ChangeNicknameScreenEvents
    data object ChangeNickname : ChangeNicknameScreenEvents
    data object OnBack : ChangeNicknameScreenEvents
}