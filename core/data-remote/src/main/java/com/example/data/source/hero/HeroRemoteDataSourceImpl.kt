package com.example.data.source.hero

import com.example.data.remote.api.hero.HeroApi
import com.example.data.remote.api.hero.model.HeroDto
import com.example.data.remote.util.NetworkResult
import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class HeroRemoteDataSourceImpl @Inject constructor(
    private val heroApi: HeroApi,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : HeroRemoteDataSource {
    override suspend fun getHeroesList(): NetworkResult<List<HeroDto>> {
        return withContext(ioDispatcher) {
            handleApi { heroApi.getHeroesList() }
        }
    }
}