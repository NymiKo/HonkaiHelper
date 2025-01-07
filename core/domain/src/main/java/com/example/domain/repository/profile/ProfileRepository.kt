package com.example.domain.repository.profile

import com.example.domain.usecase.hero.model.ProfileWithFullInfoModel
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface ProfileRepository {
    val profileFlow: StateFlow<NetworkResult<ProfileWithFullInfoModel>?>

    suspend fun getProfile()
    fun clearProfile()
}