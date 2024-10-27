package com.example.tanorami.teams.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.teams.data.model.TeamHero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsListRepositoryImpl @Inject constructor(
    private val teamsListService: TeamsListService,
    private val heroDao: HeroDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : TeamsListRepository {

    override suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHero>> = withContext(ioDispatcher) {
        when(val result = handleApi { teamsListService.getTeamsListByID(idHero) }) {
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
                        avatar = it.avatar,
                        uid = it.uid,
                    )
                })
            }
        }
    }

    override suspend fun getTeamsList(): NetworkResult<List<TeamHero>> = withContext(ioDispatcher) {
        when (val result = handleApi { teamsListService.getTeamsList() }) {
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
                        avatar = it.avatar,
                        uid = it.uid,
                    )
                })
            }
        }
    }

    override suspend fun getNameHero(idHero: Int): String = withContext(ioDispatcher) {
        return@withContext heroDao.getName(idHero)
    }
}