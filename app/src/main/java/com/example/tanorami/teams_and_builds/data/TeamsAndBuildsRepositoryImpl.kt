package com.example.tanorami.teams_and_builds.data

import com.example.data.db.dao.DecorationDao
import com.example.data.db.dao.HeroDao
import com.example.data.db.dao.RelicDao
import com.example.data.db.dao.WeaponDao
import com.example.data.remote.NetworkResult
import com.example.data.remote.handleApi
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.domain.di.IODispatcher
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListService
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.TeamsFromUsersService
import com.example.tanorami.teams.data.model.TeamHeroes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsAndBuildsRepositoryImpl @Inject constructor(
    private val buildsHeroListService: BuildsHeroListService,
    private val teamsFromUsersService: TeamsFromUsersService,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : TeamsAndBuildsRepository {
    override suspend fun getHero(idHero: Int): HeroBaseInfoModel =
        withContext(ioDispatcher) {
            return@withContext heroDao.getHeroWithNameAvatarRarity(idHero).toHeroBaseInfoModel()
        }

    override suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHeroes>> =
        withContext(ioDispatcher) {
            when (val result =
                handleApi { teamsFromUsersService.getTeamsList() }) {
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

    override suspend fun getBuildsListFromUsers(): NetworkResult<List<BuildHeroWithUser>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { buildsHeroListService.getBuildsHeroList() }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        BuildHeroWithUser(
                            idBuild = it.idBuild,
                            hero = getHero(it.idHero),
                            weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                            relicTwoParts = relicDao.getRelic(it.idRelicTwoParts).toRelic(),
                            relicFourParts = relicDao.getRelic(it.idRelicFourParts).toRelic(),
                            decoration = decorationDao.getDecoration(it.idDecoration)
                                .toDecoration(),
                            buildUser = it.buildUser!!
                        )
                    })
                }
            }
        }
}