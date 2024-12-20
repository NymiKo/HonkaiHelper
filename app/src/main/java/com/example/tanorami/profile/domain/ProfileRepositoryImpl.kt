package com.example.tanorami.profile.domain

import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel
import com.example.data.db.dao.DecorationDao
import com.example.data.db.dao.HeroDao
import com.example.data.db.dao.RelicDao
import com.example.data.db.dao.WeaponDao
import com.example.data.remote.util.handleApi
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import com.example.tanorami.profile.data.ProfileService
import com.example.tanorami.profile.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao,
) : ProfileRepository {

    private val _profileFlow: MutableStateFlow<NetworkResult<User>?> =
        MutableStateFlow(null)
    override val profileFlow: StateFlow<NetworkResult<User>?> =
        _profileFlow.asStateFlow()

    override suspend fun getProfile() {
        withContext(ioDispatcher) {
            when (val result = handleApi { profileService.getProfile() }) {
                is NetworkResult.Error -> {
                    _profileFlow.value =
                        NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    _profileFlow.value = NetworkResult.Success(User(
                        nickname = result.data.login,
                        avatarUrl = result.data.avatarUrl,
                        teamsList = result.data.teamsList.map {
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
                                userInfo = null
                            )
                        },
                        buildsHeroes = result.data.buildsHeroes.map {
                            HeroBuildModel(
                                idBuild = it.idBuild,
                                hero = heroDao.getHeroWithNameAvatarRarity(it.idHero)
                                    .toHeroBaseInfoModel(),
                                weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                                relicTwoParts = relicDao.getRelic(it.idRelicTwoParts).toRelic(),
                                relicFourParts = relicDao.getRelic(it.idRelicFourParts).toRelic(),
                                decoration = decorationDao.getDecoration(it.idDecoration)
                                    .toDecoration(),
                                buildUser = null
                            )
                        }
                    ))
                }
            }
        }
    }

    override suspend fun loadAvatar(file: File): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            when (val result = handleApi {
                profileService.loadAvatar(
                    MultipartBody.Part.createFormData(
                        "avatar",
                        file.name,
                        file.asRequestBody()
                    )
                )
            }) {
                is NetworkResult.Error -> NetworkResult.Error(
                    result.code
                )

                is NetworkResult.Success -> NetworkResult.Success(
                    result.data
                )
            }
        }
    }

    override fun clearProfile() {
        _profileFlow.value = null
    }
}