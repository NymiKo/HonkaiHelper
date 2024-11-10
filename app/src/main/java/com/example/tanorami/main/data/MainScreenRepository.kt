package com.example.tanorami.main.data

import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.main.presentation.models.NewDataModel

interface MainScreenRepository {
    suspend fun getRemoteVersionDB(): NetworkResult<NewDataModel>
    suspend fun getAvatar(): NetworkResult<String>
}