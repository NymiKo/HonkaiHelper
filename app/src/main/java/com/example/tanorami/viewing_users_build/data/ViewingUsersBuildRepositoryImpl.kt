package com.example.tanorami.viewing_users_build.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.local.dao.DecorationDao
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.data.local.dao.WeaponDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ViewingUsersBuildRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val viewingUsersBuildService: ViewingUsersBuildService,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
): ViewingUsersBuildRepository {
    override suspend fun getHeroBuild(idBuild: Long): NetworkResult<FullBuildHeroFromUser> = withContext(ioDispatcher) {
        when(val result = handleApi { viewingUsersBuildService.getHeroBuild(idBuild) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(
                    FullBuildHeroFromUser(
                        idBuild = idBuild,
                        hero = heroDao.getHeroWithNameAvatarRarity(result.data.idHero),
                        weapon = weaponDao.getWeapon(result.data.idWeapon).toWeapon(),
                        relicTwoParts = relicDao.getRelic(result.data.idRelicTwoParts).toRelic(),
                        relicFourParts = relicDao.getRelic(result.data.idRelicFourParts).toRelic(),
                        decoration = decorationDao.getDecoration(result.data.idDecoration).toDecoration(),
                        statsEquipment = result.data.statsEquipment.toList(),
                        nickname = result.data.nickname
                    )
                )
            }
        }
    }
}