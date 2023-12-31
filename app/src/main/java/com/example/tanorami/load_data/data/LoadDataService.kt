package com.example.tanorami.load_data.data

import com.example.tanorami.base_build_hero.data.model.BuildDecoration
import com.example.tanorami.base_build_hero.data.model.BuildRelic
import com.example.tanorami.base_build_hero.data.model.BuildStatsEquipment
import com.example.tanorami.base_build_hero.data.model.BuildWeapon
import com.example.tanorami.base_build_hero.data.model.OptimalStatsHero
import com.example.tanorami.base_build_hero.data.model.Weapon
import com.example.tanorami.info_about_hero.data.model.Ability
import com.example.tanorami.info_about_hero.data.model.Eidolon
import com.example.tanorami.info_about_hero.data.model.Element
import com.example.tanorami.info_about_hero.data.model.Path
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.info_about_hero.data.model.Relic
import retrofit2.Response
import retrofit2.http.GET

interface LoadDataService {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<Hero>>

    @GET("/getPaths.php")
    suspend fun getPathsList(): Response<List<Path>>

    @GET("/getElements.php")
    suspend fun getElementsList(): Response<List<Element>>

    @GET("/getAbilities.php")
    suspend fun getAbilitiesList(): Response<List<Ability>>

    @GET("/getEidolons.php")
    suspend fun getEidolonsList(): Response<List<Eidolon>>

    @GET("/getOptimalStats.php")
    suspend fun getOptimalStats(): Response<List<OptimalStatsHero>>

    @GET("/getBuildWeapons.php")
    suspend fun getBuildWeapons(): Response<List<BuildWeapon>>

    @GET("/getBuildRelics.php")
    suspend fun getBuildRelics(): Response<List<BuildRelic>>

    @GET("/getBuildDecorations.php")
    suspend fun getBuildDecorations(): Response<List<BuildDecoration>>

    @GET("/getDecorations.php")
    suspend fun getDecorations(): Response<List<Decoration>>

    @GET("/getRelics.php")
    suspend fun getRelics(): Response<List<Relic>>

    @GET("/getStatsEquipment.php")
    suspend fun getStatsEquipment(): Response<List<BuildStatsEquipment>>

    @GET("/getWeapons.php")
    suspend fun getWeapons(): Response<List<Weapon>>
}