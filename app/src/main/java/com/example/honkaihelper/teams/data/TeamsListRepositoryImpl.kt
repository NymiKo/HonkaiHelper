package com.example.honkaihelper.teams.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.teams.data.model.TeamHero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsListRepositoryImpl @Inject constructor(
    private val teamsListService: TeamsListService,
    private val ioDispatcher: CoroutineDispatcher
) : TeamsListRepository {

    override suspend fun getTeamsList(idHero: Int): NetworkResult<List<TeamHero>> {
        return withContext(ioDispatcher) {
            handleApi {
                teamsListService.getTeamsList(idHero)
            }
        }
    }
}