package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.heroes.data.HeroesListService
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val heroesListService: HeroesListService,
    private val createTeamService: CreateTeamService,
    private val ioDispatcher: CoroutineDispatcher
) : CreateTeamRepository {

    override suspend fun getHeroesList(): NetworkResult<List<Hero>> {
        return withContext(ioDispatcher) {
            handleApi {
                heroesListService.getHeroesList()
            }
        }
    }

    override suspend fun saveTeam(heroesList: List<Hero>): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            handleApi {
                createTeamService.saveTeam(heroesList.sortedBy { hero -> hero.id }
                    .map { hero -> hero.id })
            }
        }
    }
}