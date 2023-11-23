package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.honkaihelper.data.local.models.hero.HeroWithPathAndElement
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.data.local.models.hero.FullBaseBuildHeroEntity
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM ${RoomContract.tableHeroes}")
    suspend fun getHeroes(): List<HeroEntity>

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroWithPathAndElement(idHero: Int): HeroWithPathAndElement

    @Transaction
    @Query("SELECT id FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getFullBaseBuildHero(idHero: Int): FullBaseBuildHeroEntity

    @Query("SELECT name, localAvatarPath, rarity FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHeroWithNameAvatarRarity(idHero: Int): HeroWithNameAvatarRarity

    @Query("SELECT * FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getHero(idHero: Int): HeroEntity

    @Query("SELECT name FROM ${RoomContract.tableHeroes} WHERE id = :idHero")
    suspend fun getName(idHero: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroEntity>)
}