package com.example.data.source.team

import com.example.data.remote.api.team.TeamsFromUsersApi
import com.example.data.remote.api.team.model.TeamFromUserDto
import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TeamsFromUsersRemoteDataSourceImpl @Inject constructor(
    private val teamsFromUsersApi: TeamsFromUsersApi,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : TeamsFromUsersRemoteDataSource {
    override suspend fun getTeamsFromUsersListByIdHero(idHero: Int): NetworkResult<List<TeamFromUserDto>> {
        return withContext(ioDispatcher) {
            handleApi { teamsFromUsersApi.getTeamsFromUsersListByIdHero(idHero) }
        }
    }

    override suspend fun getTeamsFromUsersList(page: Int, uid: String): List<TeamFromUserDto> {
        return withContext(ioDispatcher) {
            teamsFromUsersApi.getTeamsFromUsersList(page = page, uid)
        }
    }
}