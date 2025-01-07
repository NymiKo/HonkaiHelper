package com.example.profile.data

import com.example.data.source.profile.ProfileRemoteDataSource
import com.example.domain.util.NetworkResult
import com.example.profile.domain.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : ProfileRepository {
    override suspend fun loadAvatar(file: File): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            when (val result = profileRemoteDataSource.loadAvatar(
                MultipartBody.Part.createFormData(
                    "avatar",
                    file.name,
                    file.asRequestBody()
                )
            )) {
                is NetworkResult.Error -> NetworkResult.Error(
                    result.code
                )

                is NetworkResult.Success -> NetworkResult.Success(
                    result.data
                )
            }
        }
    }
}