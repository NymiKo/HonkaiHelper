package com.example.tanorami.createteam.data

import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val createTeamService: CreateTeamService,
    private val heroDao: HeroDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : CreateTeamRepository {

    override suspend fun getHeroesList(): List<ActiveHeroInTeam> = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroWithNameAvatarRarityList().sortedBy { it.name }.map { ActiveHeroInTeam(it, false) }
    }

    override suspend fun saveTeam(idTeam: Long, heroesList: List<HeroWithNameAvatarRarity>): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            if (idTeam != -1L) {
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

    override suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroWithNameAvatarRarity>>> = withContext(ioDispatcher) {
        when(val result = handleApi { createTeamService.getTeam(idTeam) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(
                    Pair(
                        result.data.uid,
                        listOf<HeroWithNameAvatarRarity>(
                            heroDao.getHeroWithNameAvatarRarity(result.data.idHeroOne),
                            heroDao.getHeroWithNameAvatarRarity(result.data.idHeroTwo),
                            heroDao.getHeroWithNameAvatarRarity(result.data.idHeroThree),
                            heroDao.getHeroWithNameAvatarRarity(result.data.idHeroFour)
                        )
                    )
                )
            }
        }
    }

    override suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { createTeamService.deleteTeam(idTeam) }
    }
}