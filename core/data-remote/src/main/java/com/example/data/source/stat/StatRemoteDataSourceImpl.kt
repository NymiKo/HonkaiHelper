package com.example.data.source.stat

import com.example.data.remote.api.stat.StatApi
import com.example.data.remote.api.stat.model.StatDto
import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StatRemoteDataSourceImpl @Inject constructor(
    private val statApi: StatApi,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : StatRemoteDataSource {
    override suspend fun getStats(): NetworkResult<List<StatDto>> {
        return withContext(ioDispatcher) {
            handleApi { statApi.getStats() }
        }
    }
}