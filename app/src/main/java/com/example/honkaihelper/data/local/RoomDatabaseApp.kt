package com.example.honkaihelper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.honkaihelper.data.local.dao.AbilityDao
import com.example.honkaihelper.data.local.dao.DecorationDao
import com.example.honkaihelper.data.local.dao.EidolonDao
import com.example.honkaihelper.data.local.dao.ElementDao
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.dao.PathDao
import com.example.honkaihelper.data.local.dao.RelicDao
import com.example.honkaihelper.data.local.entity.AbilityEntity
import com.example.honkaihelper.data.local.entity.DecorationEntity
import com.example.honkaihelper.data.local.entity.EidolonEntity
import com.example.honkaihelper.data.local.entity.ElementEntity
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.data.local.entity.PathEntity
import com.example.honkaihelper.data.local.entity.RelicEntity

@Database(
    entities = [
        HeroEntity::class,
        PathEntity::class,
        ElementEntity::class,
        AbilityEntity::class,
        EidolonEntity::class,
        RelicEntity::class,
        DecorationEntity::class
               ],
    version = 1,
    exportSchema = true
)
abstract class RoomDatabaseApp : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun pathDao(): PathDao
    abstract fun elementDao(): ElementDao
    abstract fun abilityDao(): AbilityDao
    abstract fun eidolonDao(): EidolonDao
    abstract fun relicDao(): RelicDao
    abstract fun decorationDao(): DecorationDao
}