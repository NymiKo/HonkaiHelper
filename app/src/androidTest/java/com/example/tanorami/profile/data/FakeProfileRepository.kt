package com.example.tanorami.profile.data

import com.example.data.remote.util.NetworkResult
import com.example.tanorami.profile.data.model.User
import com.example.tanorami.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import javax.inject.Inject

//class FakeProfileRepository @Inject constructor(): ProfileRepository {
//    private val _profileFlow: MutableStateFlow<NetworkResult<User>?> =
//        MutableStateFlow(null)
//    override val profileFlow: StateFlow<NetworkResult<User>?> =
//        _profileFlow.asStateFlow()
//
//    override suspend fun getProfile() {
//
//    }
//
//    override suspend fun loadAvatar(file: File): NetworkResult<Unit> {
//        return NetworkResult.Success(Unit)
//    }
//
//    override fun clearProfile() {
//
//    }
//}