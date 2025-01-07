package com.example.teams_and_builds.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.BuildFilterModel
import com.example.common.HeroBuildModel
import com.example.common.TeamFilterModel
import com.example.common.TeamHeroModel
import com.example.common.UserAvatarAndNicknameModel
import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.data.source.hero_build.HeroBuildsFromUsersRemoteDataSource
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.team.TeamsFromUsersPagingSourceImpl
import com.example.data.source.team.TeamsFromUsersRemoteDataSource
import com.example.data.source.user.mapper.toUserAvatarAndNicknameModel
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.domain.util.NetworkResult
import com.example.teams_and_builds.domain.TeamsAndBuildsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    override fun getTeamsListFromUsers(uid: String, idHero: Int?): Flow<PagingData<TeamHeroModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                TeamsFromUsersPagingSourceImpl(
                    teamsFromUsersRemoteDataSource,
                    TeamFilterModel(uid, idHero)
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { teamFromUserDto ->
                TeamHeroModel(
                    idTeam = teamFromUserDto.idTeam,
                    heroOne = heroLocalDataSource.getHeroBaseInfo(teamFromUserDto.idHeroOne)
                        .toHeroBaseInfoModel(),
                    heroTwo = heroLocalDataSource.getHeroBaseInfo(teamFromUserDto.idHeroTwo)
                        .toHeroBaseInfoModel(),
                    heroThree = heroLocalDataSource.getHeroBaseInfo(teamFromUserDto.idHeroThree)
                        .toHeroBaseInfoModel(),
                    heroFour = heroLocalDataSource.getHeroBaseInfo(teamFromUserDto.idHeroFour)
                        .toHeroBaseInfoModel(),
                    uid = teamFromUserDto.uid,
                    userInfo = UserAvatarAndNicknameModel(
                        teamFromUserDto.avatar ?: "",
                        teamFromUserDto.nickname ?: ""
                    )
                )
            }
        }

    override suspend fun getBuildsListFromUsers(
        uid: String,
        idHero: Int?,
    ): NetworkResult<List<HeroBuildModel>> =
        withContext(ioDispatcher) {
            when (val result = heroBuildsFromUsersRemoteDataSource.getBuildsListFromUsers(
                BuildFilterModel(uid, idHero)
            )) {
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