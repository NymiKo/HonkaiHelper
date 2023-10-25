package com.example.honkaihelper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.honkaihelper.data.local.dao.AbilityDao
import com.example.honkaihelper.data.local.dao.ElementDao
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.dao.PathDao
import com.example.honkaihelper.data.local.entity.AbilityEntity
import com.example.honkaihelper.data.local.entity.ElementEntity
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.data.local.entity.PathEntity

@Database(
    entities = [HeroEntity::class, PathEntity::class, ElementEntity::class, AbilityEntity::class],
    version = 1,
    exportSchema = true
)
abstract class RoomDatabaseApp: RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun pathDao(): PathDao
    abstract fun elementDao(): ElementDao
    abstract fun abilityDao(): AbilityDao
}