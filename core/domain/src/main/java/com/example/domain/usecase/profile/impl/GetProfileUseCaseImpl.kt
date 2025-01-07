package com.example.domain.usecase.profile.impl

import com.example.domain.repository.profile.ProfileRepository
import com.example.domain.usecase.hero.model.ProfileWithFullInfoModel
import com.example.domain.usecase.profile.GetProfileUseCase
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : GetProfileUseCase {
    override val profileFlow: StateFlow<NetworkResult<ProfileWithFullInfoModel>?> =
        profileRepository.profileFlow

    override suspend fun invoke() {
        profileRepository.getProfile()
    }
}