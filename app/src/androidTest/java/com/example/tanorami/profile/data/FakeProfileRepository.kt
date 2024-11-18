package com.example.tanorami.profile.data

import com.example.tanorami.profile.data.model.User
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import javax.inject.Inject

class FakeProfileRepository @Inject constructor(): ProfileRepository {
    private val _profileFlow: MutableStateFlow<com.example.data.remote.NetworkResult<User>?> =
        MutableStateFlow(null)
    override val profileFlow: StateFlow<com.example.data.remote.NetworkResult<User>?> =
        _profileFlow.asStateFlow()

    override suspend fun getProfile() {

    }

    override suspend fun loadAvatar(file: File): com.example.data.remote.NetworkResult<Unit> {
        return com.example.data.remote.NetworkResult.Success(Unit)
    }

    override fun clearProfile() {

    }
}