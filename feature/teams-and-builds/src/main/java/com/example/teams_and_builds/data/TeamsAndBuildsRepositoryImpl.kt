package com.example.teams_and_builds.data

import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import com.example.common.UserAvatarAndNicknameModel
import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.data.source.hero_build.HeroBuildsFromUsersRemoteDataSource
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.team.TeamsFromUsersRemoteDataSource
import com.example.data.source.user.mapper.toUserAvatarAndNicknameModel
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TeamsAndBuildsRepositoryImpl @Inject constructor(
    private val heroBuildsFromUsersRemoteDataSource: HeroBuildsFromUsersRemoteDataSource,
    private val teamsFromUsersRemoteDataSource: TeamsFromUsersRemoteDataSource,
    private val heroLocalDataSource: HeroLocalDataSource,
    private val weaponLocalDataSource: WeaponLocalDataSource,
    private val relicLocalDataSource: RelicLocalDataSource,
    private val decorationLocalDataSource: DecorationLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : TeamsAndBuildsRepository {
    override suspend fun getTeamsListFromUsers(): NetworkResult<List<TeamHeroModel>> =
        withContext(ioDispatcher) {
            when (val result = teamsFromUsersRemoteDataSource.getTeamsFromUsersList()) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        TeamHeroModel(
                            idTeam = it.idTeam,
                            heroOne = heroLocalDataSource.getHeroBaseInfo(it.idHeroOne)
                                .toHeroBaseInfoModel(),
                            heroTwo = heroLocalDataSource.getHeroBaseInfo(it.idHeroTwo)
                                .toHeroBaseInfoModel(),
                            heroThree = heroLocalDataSource.getHeroBaseInfo(it.idHeroThree)
                                .toHeroBaseInfoModel(),
                            heroFour = heroLocalDataSource.getHeroBaseInfo(it.idHeroFour)
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

    override suspend fun getBuildsListFromUsers(): NetworkResult<List<com.example.common.HeroBuildModel>> =
        withContext(ioDispatcher) {
            when (val result = heroBuildsFromUsersRemoteDataSource.getBuildsListFromUsers()) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        HeroBuildModel(
                            idBuild = it.idBuild,
                            hero = heroLocalDataSource.getHeroBaseInfo(it.idHero)
                                .toHeroBaseInfoModel(),
                            weapon = weaponLocalDataSource.getWeapon(it.idWeapon).toWeapon(),
                            relicTwoParts = relicLocalDataSource.getRelic(it.idRelicTwoParts)
                                .toRelic(),
                            relicFourParts = relicLocalDataSource.getRelic(it.idRelicFourParts)
                                .toRelic(),
                            decoration = decorationLocalDataSource.getDecoration(it.idDecoration)
                                .toDecoration(),
                            buildUser = it.buildUser?.toUserAvatarAndNicknameModel()
                        )
                    })
                }
            }
        }
}