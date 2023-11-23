package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.HeroesListService
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val createTeamService: CreateTeamService,
    private val heroDao: HeroDao,
    private val ioDispatcher: CoroutineDispatcher
) : CreateTeamRepository {

    override suspend fun getHeroesList(): List<ActiveHeroInTeam> = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroes().sortedBy { it.name }.map { ActiveHeroInTeam(it.toHero(), false) }
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