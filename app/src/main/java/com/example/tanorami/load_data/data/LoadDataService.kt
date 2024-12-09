package com.example.tanorami.load_data.data

import com.example.common.DecorationModel
import com.example.common.RelicModel
import com.example.common.WeaponModel
import com.example.data.remote.api.hero.model.HeroDto
import com.example.data.remote.api.stat.model.StatDto
import com.example.data.remote.api.stats_equipment.BuildStatsEquipmentDto
import com.example.domain.repository.ability.AbilityModel
import com.example.domain.repository.decoration.BuildDecorationModel
import com.example.domain.repository.eidolon.EidolonModel
import com.example.domain.repository.element.ElementModel
import com.example.domain.repository.path.PathModel
import com.example.domain.repository.relic.BuildRelicModel
import com.example.domain.repository.weapon.models.BuildWeaponModel
import retrofit2.Response
import retrofit2.http.GET

interface LoadDataService {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<HeroDto>>

    @GET("/getPaths.php")
    suspend fun getPathsList(): Response<List<PathModel>>

    @GET("/getElements.php")
    suspend fun getElementsList(): Response<List<ElementModel>>

    @GET("/getAbilities.php")
    suspend fun getAbilitiesList(): Response<List<AbilityModel>>

    @GET("/getEidolons.php")
    suspend fun getEidolonsList(): Response<List<EidolonModel>>

//    @GET("/getOptimalStats.php")
//    suspend fun getOptimalStats(): Response<List<OptimalStatsHero>>

    @GET("/getBuildWeapons.php")
    suspend fun getBuildWeapons(): Response<List<BuildWeaponModel>>

    @GET("/getBuildRelics.php")
    suspend fun getBuildRelics(): Response<List<BuildRelicModel>>

    @GET("/getBuildDecorations.php")
    suspend fun getBuildDecorations(): Response<List<BuildDecorationModel>>

    @GET("/getDecorations.php")
    suspend fun getDecorations(): Response<List<DecorationModel>>

    @GET("/getRelics.php")
    suspend fun getRelics(): Response<List<RelicModel>>

    @GET("/getStatsEquipment.php")
    suspend fun getStatsEquipment(): Response<List<BuildStatsEquipmentDto>>

    @GET("/getWeapons.php")
    suspend fun getWeapons(): Response<List<WeaponModel>>

    @GET("tables/getStats.php")
    suspend fun getStats(): Response<List<StatDto>>
}