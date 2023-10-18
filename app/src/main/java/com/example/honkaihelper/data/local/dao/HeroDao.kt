package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.heroes.data.model.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM ${RoomContract.tableHeroes}")
    suspend fun getHeroes(): List<HeroEntity>

    @Insert
    suspend fun insertHeroes(heroes: List<HeroEntity>)

}