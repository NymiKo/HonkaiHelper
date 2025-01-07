package com.example.data.source.profile

import com.example.data.remote.api.profile.ProfileApi
import com.example.data.remote.api.profile.model.ProfileDto
import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : ProfileRemoteDataSource {
    override suspend fun getProfile(): NetworkResult<ProfileDto> {
        return withContext(ioDispatcher) {
            handleApi { profileApi.getProfile() }
        }
    }

    override suspend fun loadAvatar(avatar: MultipartBody.Part): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            handleApi { profileApi.loadAvatar(avatar) }
        }
    }
}