package com.example.honkaihelper.profile.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.profile.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {
    override suspend fun getProfile(): NetworkResult<User> {
        return withContext(ioDispatcher) {
            handleApi {
                profileService.getProfile()
            }
        }
    }

    override suspend fun loadAvatar(file: File): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            handleApi {
                profileService.loadAvatar(MultipartBody.Part.createFormData("avatar", file.name, file.asRequestBody()))
            }
        }
    }
}