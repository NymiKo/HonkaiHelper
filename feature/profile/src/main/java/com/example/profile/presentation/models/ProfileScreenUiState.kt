package com.example.profile.presentation.models

import com.example.base.UiState
import com.example.domain.usecase.hero.model.ProfileWithFullInfoModel
import com.example.strings.R

data class ProfileScreenUiState(
    val isLoading: Boolean = false,
    val isAuthorized: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: Int = R.string.error,
    val user: ProfileWithFullInfoModel = ProfileWithFullInfoModel(),
) : UiState
