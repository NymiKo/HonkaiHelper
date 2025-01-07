package com.example.domain.usecase.profile

import com.example.domain.usecase.hero.model.ProfileWithFullInfoModel
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface GetProfileUseCase {
    val profileFlow: StateFlow<NetworkResult<ProfileWithFullInfoModel>?>
    suspend operator fun invoke()
}