package com.example.data.source.hero_build

import com.example.data.remote.api.build.HeroBuildsFromUsersApi
import com.example.data.remote.api.build.model.HeroBuildFromUserDto
import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class HeroBuildsFromUsersRemoteDataSourceImpl @Inject constructor(
    private val heroBuildsFromUsersApi: HeroBuildsFromUsersApi,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : HeroBuildsFromUsersRemoteDataSource {
    override suspend fun getHeroBuildsFromUsersByIdHero(idHero: Int): NetworkResult<List<HeroBuildFromUserDto>> {
        return withContext(ioDispatcher) {
            handleApi { heroBuildsFromUsersApi.getHeroBuildsFromUsersByIdHero(idHero) }
        }
    }

    override suspend fun getBuildsListFromUsers(): NetworkResult<List<HeroBuildFromUserDto>> {
        return withContext(ioDispatcher) {
            handleApi { heroBuildsFromUsersApi.getHeroBuildsFromUsersList() }
        }
    }
}