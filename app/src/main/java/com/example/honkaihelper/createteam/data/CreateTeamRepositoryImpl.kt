package com.example.honkaihelper.createteam.data

import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.HeroesListService
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val createTeamService: CreateTeamService,
    private val heroDao: HeroDao,
    private val ioDispatcher: CoroutineDispatcher
) : CreateTeamRepository {

    override suspend fun getHeroesList(): List<ActiveHeroInTeam> = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroWithNameAvatarRarityList().sortedBy { it.name }.map { ActiveHeroInTeam(it, false) }
    }

    override suspend fun saveTeam(idTeam: Int, heroesList: List<HeroWithNameAvatarRarity>): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            if (idTeam != -1) {
                handleApi { createTeamService.updateTeam(idTeam,
                    heroesList.sortedBy { hero -> hero.id }.map { hero -> hero.id })
                }
            } else {
                handleApi {
                    createTeamService.saveTeam(heroesList.sortedBy { hero -> hero.id }
                        .map { hero -> hero.id })
                }
            }
        }
    }

    override suspend fun getTeam(idTeam: Int): NetworkResult<List<HeroWithNameAvatarRarity>> = withContext(ioDispatcher) {
        when(val result = handleApi { createTeamService.getTeam(idTeam) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(
                    listOf<HeroWithNameAvatarRarity>(
                        heroDao.getHeroWithNameAvatarRarity(result.data.idHeroOne),
                        heroDao.getHeroWithNameAvatarRarity(result.data.idHeroTwo),
                        heroDao.getHeroWithNameAvatarRarity(result.data.idHeroThree),
                        heroDao.getHeroWithNameAvatarRarity(result.data.idHeroFour)
                    )
                )
            }
        }
    }

    override suspend fun deleteTeam(idTeam: Int): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { createTeamService.deleteTeam(idTeam) }
    }
}