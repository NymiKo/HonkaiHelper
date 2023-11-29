package com.example.honkaihelper.teams.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.teams.data.model.TeamHeroResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsListRepositoryImpl @Inject constructor(
    private val teamsListService: TeamsListService,
    private val heroDao: HeroDao,
    private val ioDispatcher: CoroutineDispatcher
) : TeamsListRepository {

    override suspend fun getTeamsList(idHero: Int): NetworkResult<List<TeamHero>> = withContext(ioDispatcher) {
        when(val result = handleApi { teamsListService.getTeamsList(idHero) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(result.data.map {
                    TeamHero(
                        idTeam = it.idTeam,
                        heroOne = heroDao.getHeroWithNameAvatarRarity(it.idHeroOne),
                        heroTwo = heroDao.getHeroWithNameAvatarRarity(it.idHeroTwo),
                        heroThree = heroDao.getHeroWithNameAvatarRarity(it.idHeroThree),
                        heroFour = heroDao.getHeroWithNameAvatarRarity(it.idHeroFour),
                        nickname = it.nickname,
                        avatar = it.avatar
                    )
                })
            }
        }
    }

    override suspend fun getNameHero(idHero: Int): String = withContext(ioDispatcher) {
        return@withContext heroDao.getName(idHero)
    }
}