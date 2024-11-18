package com.example.tanorami.main.data

import com.example.tanorami.main.presentation.models.NewDataModel

interface MainScreenRepository {
    suspend fun getRemoteVersionDB(): com.example.data.remote.NetworkResult<NewDataModel>
    suspend fun getAvatar(): com.example.data.remote.NetworkResult<String>
}