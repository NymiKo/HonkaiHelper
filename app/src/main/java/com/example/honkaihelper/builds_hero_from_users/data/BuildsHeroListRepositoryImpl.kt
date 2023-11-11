package com.example.honkaihelper.builds_hero_from_users.data

import com.example.honkaihelper.builds_hero_from_users.data.model.FullBuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.DecorationDao
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.dao.RelicDao
import com.example.honkaihelper.data.local.dao.WeaponDao
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BuildsHeroListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val buildsHeroListService: BuildsHeroListService,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
): BuildsHeroListRepository {
    override suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroWithNameAvatarRarity(idHero)
    }

    override suspend fun getBuildsHeroList(idHero: Int): List<FullBuildHeroFromUser> = withContext(ioDispatcher) {
        when(val result = handleApi { buildsHeroListService.getBuildsHeroList(idHero) }) {
            is NetworkResult.Error -> {
                return@withContext emptyList()
            }
            is NetworkResult.Success -> {
                val hero = getHero(idHero)
                return@withContext result.data.map {
                    FullBuildHeroFromUser(
                        idBuild = it.idBuild,
                        hero = hero,
                        weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                        relic = relicDao.getRelic(it.idRelic).toRelic(),
                        decoration = decorationDao.getDecoration(it.idDecoration).toDecoration(),
                        buildUser = it.buildUser
                    )
                }
            }
        }
    }
}