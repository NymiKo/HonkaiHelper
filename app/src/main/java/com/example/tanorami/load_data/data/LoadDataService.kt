package com.example.tanorami.load_data.data

import com.example.domain.repository.ability.AbilityModel
import com.example.domain.repository.build_stats_equipment.BuildStatsEquipmentModel
import com.example.domain.repository.decoration.BuildDecorationModel
import com.example.domain.repository.decoration.DecorationModel
import com.example.domain.repository.eidolon.EidolonModel
import com.example.domain.repository.element.ElementModel
import com.example.domain.repository.hero.model.HeroModel
import com.example.domain.repository.optimal_stats_hero.OptimalStatsHero
import com.example.domain.repository.path.PathModel
import com.example.domain.repository.relic.BuildRelicModel
import com.example.domain.repository.relic.RelicModel
import com.example.domain.repository.weapon.BuildWeaponModel
import com.example.domain.repository.weapon.WeaponModel
import retrofit2.Response
import retrofit2.http.GET

interface LoadDataService {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<HeroModel>>

    @GET("/getPaths.php")
    suspend fun getPathsList(): Response<List<PathModel>>

    @GET("/getElements.php")
    suspend fun getElementsList(): Response<List<ElementModel>>

    @GET("/getAbilities.php")
    suspend fun getAbilitiesList(): Response<List<AbilityModel>>

    @GET("/getEidolons.php")
    suspend fun getEidolonsList(): Response<List<EidolonModel>>

    @GET("/getOptimalStats.php")
    suspend fun getOptimalStats(): Response<List<OptimalStatsHero>>

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
    suspend fun getStatsEquipment(): Response<List<BuildStatsEquipmentModel>>

    @GET("/getWeapons.php")
    suspend fun getWeapons(): Response<List<WeaponModel>>
}