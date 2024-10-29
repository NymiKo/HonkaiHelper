package com.example.tanorami.viewing_users_build.data

import com.example.tanorami.core.data.NetworkResult
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface ViewingBuildHeroFromUserRepository {
    suspend fun getHeroBuildByID(idBuild: Long): NetworkResult<FullBuildHeroFromUser>
}