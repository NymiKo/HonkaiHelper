package com.example.honkaihelper.activity.data

import com.example.honkaihelper.data.NetworkResult

interface MainRepository {
    suspend fun getRemoteVersionDB(): NetworkResult<String>
}