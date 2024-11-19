package com.example.tanorami.teams.data

import com.example.data.db.dao.HeroDao
import com.example.data.remote.NetworkResult
import com.example.data.remote.handleApi
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.domain.di.IODispatcher
import com.example.tanorami.teams.data.model.TeamHeroes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsFromUsersRepositoryImpl @Inject constructor(
    private val teamsFromUsersService: TeamsFromUsersService,
    private val heroDao: HeroDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : TeamsFromUsersRepository {

    override suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHeroes>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { teamsFromUsersService.getTeamsListByID(idHero) }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        TeamHeroes(
                            idTeam = it.idTeam,
                            heroOne = heroDao.getHeroWithNameAvatarRarity(it.idHeroOne)
                                .toHeroBaseInfoModel(),
                            heroTwo = heroDao.getHeroWithNameAvatarRarity(it.idHeroTwo)
                                .toHeroBaseInfoModel(),
                            heroThree = heroDao.getHeroWithNameAvatarRarity(it.idHeroThree)
                                .toHeroBaseInfoModel(),
                            heroFour = heroDao.getHeroWithNameAvatarRarity(it.idHeroFour)
                                .toHeroBaseInfoModel(),
                            nickname = it.nickname,
                            avatar = it.avatar,
                            uid = it.uid,
                        )
                    })
                }
            }
        }

    override suspend fun getTeamsList(): NetworkResult<List<TeamHeroes>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { teamsFromUsersService.getTeamsList() }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        TeamHeroes(
                            idTeam = it.idTeam,
                            heroOne = heroDao.getHeroWithNameAvatarRarity(it.idHeroOne)
                                .toHeroBaseInfoModel(),
                            heroTwo = heroDao.getHeroWithNameAvatarRarity(it.idHeroTwo)
                                .toHeroBaseInfoModel(),
                            heroThree = heroDao.getHeroWithNameAvatarRarity(it.idHeroThree)
                                .toHeroBaseInfoModel(),
                            heroFour = heroDao.getHeroWithNameAvatarRarity(it.idHeroFour)
                                .toHeroBaseInfoModel(),
                            nickname = it.nickname,
                            avatar = it.avatar,
                            uid = it.uid,
                        )
                    })
                }
            }
        }

    override suspend fun getNameHero(idHero: Int): String = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroNameById(idHero)
    }
}