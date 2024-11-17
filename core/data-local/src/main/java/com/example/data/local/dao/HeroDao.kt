package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.contract.RoomContract
import com.example.data.local.entity.HeroEntity
import com.example.data.local.models.hero.HeroBaseInfoProjection
import com.example.data.local.models.hero.HeroFullBaseBuildRelations
import com.example.data.local.models.hero.HeroFullInfoRelations

@Dao
interface HeroDao {

    @Query("SELECT * FROM ${RoomContract.tableHeroes}")
    suspend fun getHeroesList(): List<HeroEntity>

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroFullInfoRelations

    @Transaction
    @Query("SELECT id FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getFullBaseBuildHero(idHero: Int): HeroFullBaseBuildRelations

    @Query("SELECT id, name, localAvatarPath, rarity FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroWithNameAvatarRarity(idHero: Int): HeroBaseInfoProjection

    @Query("SELECT id, name, localAvatarPath, rarity FROM ${RoomContract.tableHeroes}")
    suspend fun getHeroesListWithNameAvatarRarity(): List<HeroBaseInfoProjection>

    @Query("SELECT * FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroById(idHero: Int): HeroEntity

    @Query("SELECT name FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroNameById(idHero: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroesList(heroes: List<HeroEntity>)
}