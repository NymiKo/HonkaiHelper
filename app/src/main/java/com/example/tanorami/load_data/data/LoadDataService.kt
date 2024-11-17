package com.example.tanorami.load_data.data

import retrofit2.Response
import retrofit2.http.GET

interface LoadDataService {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<com.example.domain.repository.hero.model.HeroModel>>

    @GET("/getPaths.php")
    suspend fun getPathsList(): Response<List<com.example.domain.repository.path.Path>>

    @GET("/getElements.php")
    suspend fun getElementsList(): Response<List<com.example.domain.repository.element.Element>>

    @GET("/getAbilities.php")
    suspend fun getAbilitiesList(): Response<List<com.example.domain.repository.ability.Ability>>

    @GET("/getEidolons.php")
    suspend fun getEidolonsList(): Response<List<com.example.domain.repository.eidolon.Eidolon>>

    @GET("/getOptimalStats.php")
    suspend fun getOptimalStats(): Response<List<com.example.domain.repository.optimal_stats_hero.OptimalStatsHero>>

    @GET("/getBuildWeapons.php")
    suspend fun getBuildWeapons(): Response<List<com.example.domain.repository.weapon.BuildWeapon>>

    @GET("/getBuildRelics.php")
    suspend fun getBuildRelics(): Response<List<com.example.domain.repository.relic.BuildRelic>>

    @GET("/getBuildDecorations.php")
    suspend fun getBuildDecorations(): Response<List<com.example.domain.repository.decoration.BuildDecoration>>

    @GET("/getDecorations.php")
    suspend fun getDecorations(): Response<List<com.example.domain.repository.decoration.Decoration>>

    @GET("/getRelics.php")
    suspend fun getRelics(): Response<List<com.example.domain.repository.relic.Relic>>

    @GET("/getStatsEquipment.php")
    suspend fun getStatsEquipment(): Response<List<com.example.domain.repository.build_stats_equipment.BuildStatsEquipment>>

    @GET("/getWeapons.php")
    suspend fun getWeapons(): Response<List<com.example.domain.repository.weapon.Weapon>>
}