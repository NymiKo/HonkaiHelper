package com.example.domain.usecase.profile.impl

import com.example.domain.repository.profile.ProfileRepository
import com.example.domain.usecase.profile.LogoutAccountUseCase
import javax.inject.Inject

class LogoutAccountUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : LogoutAccountUseCase {
    override fun invoke() {
        profileRepository.clearProfile()
    }
}