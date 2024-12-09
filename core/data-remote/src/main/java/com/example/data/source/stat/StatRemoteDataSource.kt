package com.example.data.source.stat

import com.example.data.remote.api.stat.model.StatDto
import com.example.domain.util.NetworkResult

interface StatRemoteDataSource {
    suspend fun getStats(): NetworkResult<List<StatDto>>
}