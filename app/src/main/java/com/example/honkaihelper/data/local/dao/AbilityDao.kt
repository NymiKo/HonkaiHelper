package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.AbilityEntity

@Dao
interface AbilityDao {

    @Query("SELECT * FROM ${RoomContract.tableAbilities}")
    suspend fun getAbilities(): List<AbilityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>)
}