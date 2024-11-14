package com.example.tanorami.profile.domain

import com.example.core.database.dao.DecorationDao
import com.example.core.database.dao.HeroDao
import com.example.core.database.dao.RelicDao
import com.example.core.database.dao.WeaponDao
import com.example.core.di.IODispatcher
import com.example.core.network.NetworkResult
import com.example.core.network.handleApi
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.profile.data.ProfileService
import com.example.tanorami.profile.data.model.User
import com.example.tanorami.teams.data.model.TeamHero
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
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao,
) : ProfileRepository {

    private val _profileFlow: MutableStateFlow<NetworkResult<User>?> = MutableStateFlow(null)
    override val profileFlow: StateFlow<NetworkResult<User>?> = _profileFlow.asStateFlow()

    override suspend fun getProfile() {
        withContext(ioDispatcher) {
            when (val result = handleApi { profileService.getProfile() }) {
                is NetworkResult.Error -> {
                    _profileFlow.value = NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    _profileFlow.value = NetworkResult.Success(User(
                        nickname = result.data.login,
                        avatarUrl = result.data.avatarUrl,
                        teamsList = result.data.teamsList.map {
                            TeamHero(
                                idTeam = it.idTeam,
                                heroOne = heroDao.getHeroWithNameAvatarRarity(it.idHeroOne),
                                heroTwo = heroDao.getHeroWithNameAvatarRarity(it.idHeroTwo),
                                heroThree = heroDao.getHeroWithNameAvatarRarity(it.idHeroThree),
                                heroFour = heroDao.getHeroWithNameAvatarRarity(it.idHeroFour),
                                nickname = it.nickname,
                                avatar = it.avatar
                            )
                        },
                        buildsHeroes = result.data.buildsHeroes.map {
                            BuildHeroWithUser(
                                idBuild = it.idBuild,
                                hero = heroDao.getHeroWithNameAvatarRarity(it.idHero),
                                weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                                relicTwoParts = relicDao.getRelic(it.idRelicTwoParts).toRelic(),
                                relicFourParts = relicDao.getRelic(it.idRelicFourParts).toRelic(),
                                decoration = decorationDao.getDecoration(it.idDecoration)
                                    .toDecoration(),
                                buildUser = it.buildUser
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
                is NetworkResult.Error -> NetworkResult.Error(result.code)
                is NetworkResult.Success -> NetworkResult.Success(result.data)
            }
        }
    }

    override fun clearProfile() {
        _profileFlow.value = null
    }
}