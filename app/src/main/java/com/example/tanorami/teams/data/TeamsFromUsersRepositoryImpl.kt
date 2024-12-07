package com.example.tanorami.teams.data

import com.example.common.TeamHeroModel
import com.example.common.UserAvatarAndNicknameModel
import com.example.data.db.dao.HeroDao
import com.example.data.remote.util.handleApi
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsFromUsersRepositoryImpl @Inject constructor(
    private val teamsFromUsersService: TeamsFromUsersService,
    private val heroDao: HeroDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : TeamsFromUsersRepository {

    override suspend fun getTeamsListByID(idHero: Int): NetworkResult<List<TeamHeroModel>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { teamsFromUsersService.getTeamsListByID(idHero) }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        TeamHeroModel(
                            idTeam = it.idTeam,
                            heroOne = heroDao.getHeroWithNameAvatarRarity(it.idHeroOne)
                                .toHeroBaseInfoModel(),
                            heroTwo = heroDao.getHeroWithNameAvatarRarity(it.idHeroTwo)
                                .toHeroBaseInfoModel(),
                            heroThree = heroDao.getHeroWithNameAvatarRarity(it.idHeroThree)
                                .toHeroBaseInfoModel(),
                            heroFour = heroDao.getHeroWithNameAvatarRarity(it.idHeroFour)
                                .toHeroBaseInfoModel(),
                            uid = it.uid,
                            userInfo = UserAvatarAndNicknameModel(
                                it.avatar ?: "",
                                it.nickname ?: ""
                            )
                        )
                    })
                }
            }
        }

    override suspend fun getTeamsList(): NetworkResult<List<TeamHeroModel>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { teamsFromUsersService.getTeamsList() }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        TeamHeroModel(
                            idTeam = it.idTeam,
                            heroOne = heroDao.getHeroWithNameAvatarRarity(it.idHeroOne)
                                .toHeroBaseInfoModel(),
                            heroTwo = heroDao.getHeroWithNameAvatarRarity(it.idHeroTwo)
                                .toHeroBaseInfoModel(),
                            heroThree = heroDao.getHeroWithNameAvatarRarity(it.idHeroThree)
                                .toHeroBaseInfoModel(),
                            heroFour = heroDao.getHeroWithNameAvatarRarity(it.idHeroFour)
                                .toHeroBaseInfoModel(),
                            uid = it.uid,
                            userInfo = UserAvatarAndNicknameModel(
                                it.avatar ?: "",
                                it.nickname ?: ""
                            )
                        )
                    })
                }
            }
        }

    override suspend fun getNameHero(idHero: Int): String = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroNameById(idHero)
    }
}