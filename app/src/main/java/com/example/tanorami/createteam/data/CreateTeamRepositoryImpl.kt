package com.example.tanorami.createteam.data

import com.example.core.database.dao.HeroDao
import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.di.IODispatcher
import com.example.core.network.NetworkResult
import com.example.core.network.handleApi
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val createTeamService: CreateTeamService,
    private val heroDao: HeroDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : CreateTeamRepository {

    override suspend fun getHeroesList(): List<ActiveHeroInTeam> = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroesListWithNameAvatarRarity().sortedBy { it.name }
            .map { ActiveHeroInTeam(it, false) }
    }

    override suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoProjection>
    ): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            if (idTeam != -1L) {
                handleApi {
                    createTeamService.updateTeam(idTeam, heroesList.map { hero -> hero.id })
                }
            } else {
                handleApi {
                    createTeamService.saveTeam(heroesList.map { hero -> hero.id })
                }
            }
        }
    }

    override suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroBaseInfoProjection>>> =
        withContext(ioDispatcher) {
        when(val result = handleApi { createTeamService.getTeam(idTeam) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(
                    Pair(
                        result.data.uid,
                        listOf<HeroBaseInfoProjection>(
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