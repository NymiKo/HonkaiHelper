package com.example.tanorami.activity.data

import com.example.tanorami.data.NetworkResult

interface MainRepository {
    suspend fun getRemoteVersionDB(): NetworkResult<String>
}