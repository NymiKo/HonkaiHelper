package com.example.tanorami.viewing_users_build.data

import com.example.domain.util.NetworkResult
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface ViewingBuildHeroFromUserRepository {
    suspend fun getHeroBuildByID(idBuild: Long): NetworkResult<FullBuildHeroFromUser>
}