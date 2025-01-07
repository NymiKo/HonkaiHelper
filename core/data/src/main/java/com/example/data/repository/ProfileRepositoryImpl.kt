package com.example.data.repository

import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.data.source.profile.ProfileRemoteDataSource
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.domain.di.DispatcherIo
import com.example.domain.repository.profile.ProfileRepository
import com.example.domain.usecase.hero.model.ProfileWithFullInfoModel
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val heroLocalDataSource: HeroLocalDataSource,
    private val weaponLocalDataSource: WeaponLocalDataSource,
    private val relicLocalDataSource: RelicLocalDataSource,
    private val decorationLocalDataSource: DecorationLocalDataSource,
) : ProfileRepository {
    private val _profileFlow: MutableStateFlow<NetworkResult<ProfileWithFullInfoModel>?> =
        MutableStateFlow(null)
    override val profileFlow: StateFlow<NetworkResult<ProfileWithFullInfoModel>?> =
        _profileFlow.asStateFlow()

    override suspend fun getProfile() {
        withContext(ioDispatcher) {
            when (val result = profileRemoteDataSource.getProfile()) {
                is NetworkResult.Error -> {
                    _profileFlow.value =
                        NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    _profileFlow.value = NetworkResult.Success(ProfileWithFullInfoModel(
                        nickname = result.data.login,
                        avatarUrl = result.data.avatarUrl,
                        teamsList = result.data.teamsList.map {
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
                                userInfo = null
                            )
                        },
                        buildsHeroes = result.data.buildsHeroes.map {
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
                                buildUser = null
                            )
                        }
                    ))
                }
            }
        }
    }

    override fun clearProfile() {
        _profileFlow.value = null
    }
}