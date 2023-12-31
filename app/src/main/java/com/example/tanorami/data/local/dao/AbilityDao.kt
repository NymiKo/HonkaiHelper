package com.example.tanorami.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tanorami.data.local.contract.RoomContract
import com.example.tanorami.data.local.entity.AbilityEntity

@Dao
interface AbilityDao {

    @Query("SELECT * FROM ${RoomContract.tableAbilities}")
    suspend fun getAbilities(): List<AbilityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>)
}