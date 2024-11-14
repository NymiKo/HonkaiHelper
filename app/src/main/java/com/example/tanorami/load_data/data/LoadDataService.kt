package com.example.tanorami.load_data.data

import com.example.core.domain.repository.decoration.BuildDecoration
import com.example.core.domain.repository.relic.BuildRelic
import com.example.core.domain.repository.stats_equipment.BuildStatsEquipment
import com.example.core.domain.repository.weapon.BuildWeapon
import com.example.core.domain.repository.optimal_stats_hero.OptimalStatsHero
import com.example.core.domain.repository.ability.Ability
import com.example.core.domain.repository.decoration.Decoration
import com.example.core.domain.repository.eidolon.Eidolon
import com.example.core.domain.repository.element.Element
import com.example.core.domain.repository.hero.model.HeroModel
import com.example.core.domain.repository.path.Path
import com.example.core.domain.repository.relic.Relic
import com.example.core.domain.repository.weapon.Weapon
import retrofit2.Response
import retrofit2.http.GET

interface LoadDataService {
    @GET("/getHeroes.php")
    suspend fun getHeroesList(): Response<List<HeroModel>>

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