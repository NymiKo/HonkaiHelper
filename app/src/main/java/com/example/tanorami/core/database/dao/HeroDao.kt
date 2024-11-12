package com.example.tanorami.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tanorami.core.database.contract.RoomContract
import com.example.tanorami.core.database.entity.HeroEntity
import com.example.tanorami.core.database.models.hero.FullBaseBuildHeroEntity
import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.core.database.models.hero.HeroWithPathAndElement

@Dao
interface HeroDao {

    @Query("SELECT * FROM ${RoomContract.tableHeroes}")
    suspend fun getHeroesList(): List<HeroEntity>

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroWithPathAndElement

    @Transaction
    @Query("SELECT id FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getFullBaseBuildHero(idHero: Int): FullBaseBuildHeroEntity

    @Query("SELECT id, name, localAvatarPath, rarity FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroWithNameAvatarRarity(idHero: Int): HeroWithNameAvatarRarity

    @Query("SELECT id, name, localAvatarPath, rarity FROM ${RoomContract.tableHeroes}")
    suspend fun getHeroesListWithNameAvatarRarity(): List<HeroWithNameAvatarRarity>

    @Query("SELECT * FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroById(idHero: Int): HeroEntity

    @Query("SELECT name FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroNameById(idHero: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroesList(heroes: List<HeroEntity>)
}