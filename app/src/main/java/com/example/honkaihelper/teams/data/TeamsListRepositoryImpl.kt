package com.example.honkaihelper.teams.data

import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.models.TeamHero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsListRepositoryImpl @Inject constructor(
    private val teamsListService: TeamsListService,
    private val ioDispatcher: CoroutineDispatcher
) : TeamsListRepository {

    override suspend fun getTeamsList(idHero: Int): Result<List<TeamHero>> {
        return withContext(ioDispatcher) {
            return@withContext handleApi {
                teamsListService.getTeamsList(idHero).sortedBy { it.idTeam }
            }
        }
    }
}