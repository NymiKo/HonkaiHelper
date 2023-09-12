package com.example.honkaihelper.teams.data

import android.util.Log
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.models.TeamHero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsListRepositoryImpl @Inject constructor(
    private val teamsListService: TeamsListService,
    private val ioDispatcher: CoroutineDispatcher
): TeamsListRepository {

    override suspend fun getTeamsList(idHero: Int): List<TeamHero> {
        return withContext(ioDispatcher) {
            return@withContext try {
                teamsListService.getTeamsList(idHero)
            } catch (e: Exception) {
                // TODO: Добавить обработку ошибок
                Log.e("TEAMS_LIST_EMPTY", e.message.toString())
                emptyList<TeamHero>()
            }
        }
    }
}