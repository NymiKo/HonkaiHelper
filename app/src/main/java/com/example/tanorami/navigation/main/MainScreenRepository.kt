package com.example.tanorami.navigation.main

import com.example.tanorami.data.NetworkResult

interface MainScreenRepository {
    suspend fun getRemoteVersionDB(): NetworkResult<String>
}