package com.example.honkaihelper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.entity.ElementEntity
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.data.local.entity.PathEntity

@Database(
    entities = [HeroEntity::class, PathEntity::class, ElementEntity::class],
    version = 1,
    exportSchema = true
)
abstract class RoomDatabaseApp: RoomDatabase() {
    abstract fun heroDao(): HeroDao
}