package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.data.db.entity.AdditionalStatEntity

@Dao
interface AdditionalStatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdditionalStats(additionalStats: List<AdditionalStatEntity>)
}