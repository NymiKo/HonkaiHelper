package com.example.tanorami.teams_and_builds.data

import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListService
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.local.dao.DecorationDao
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.data.local.dao.WeaponDao
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.teams.data.TeamsListService
import com.example.tanorami.teams.data.model.TeamHero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamsAndBuildsRepositoryImpl @Inject constructor(
    private val buildsHeroListService: BuildsHeroListService,
    private val teamsListService: TeamsListService,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : TeamsAndBuildsRepository {
    override suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity =
        withContext(ioDispatcher) {
            return@withContext heroDao.getHeroWithNameAvatarRarity(idHero)
        }

    override suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHero>> =
        withContext(ioDispatcher) {
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