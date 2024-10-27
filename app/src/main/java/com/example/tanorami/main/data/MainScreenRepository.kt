package com.example.tanorami.main.data

import com.example.tanorami.data.NetworkResult

interface MainScreenRepository {
    suspend fun getRemoteVersionDB(): NetworkResult<String>
    suspend fun getAvatar(): NetworkResult<String>
}