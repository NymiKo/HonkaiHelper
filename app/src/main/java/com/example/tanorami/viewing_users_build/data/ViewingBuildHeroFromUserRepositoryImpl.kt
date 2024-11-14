package com.example.tanorami.viewing_users_build.data

import com.example.core.database.dao.DecorationDao
import com.example.core.database.dao.HeroDao
import com.example.core.database.dao.RelicDao
import com.example.core.database.dao.WeaponDao
import com.example.core.di.IODispatcher
import com.example.core.network.NetworkResult
import com.example.core.network.handleApi
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ViewingBuildHeroFromUserRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val viewingBuildHeroFromUserService: ViewingBuildHeroFromUserService,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
) : ViewingBuildHeroFromUserRepository {
    override suspend fun getHeroBuildByID(idBuild: Long): NetworkResult<FullBuildHeroFromUser> = withContext(ioDispatcher) {
        when (val result =
            handleApi { viewingBuildHeroFromUserService.getHeroBuildByID(idBuild) }) {
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
                        nickname = result.data.nickname,
                        uid = result.data.uid
                    )
                )
            }
        }
    }
}