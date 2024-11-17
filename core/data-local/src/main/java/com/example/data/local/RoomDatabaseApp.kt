package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.AbilityDao
import com.example.data.local.dao.BuildDecorationDao
import com.example.data.local.dao.BuildRelicDao
import com.example.data.local.dao.BuildStatsEquipmentDao
import com.example.data.local.dao.BuildWeaponDao
import com.example.data.local.dao.DecorationDao
import com.example.data.local.dao.EidolonDao
import com.example.data.local.dao.ElementDao
import com.example.data.local.dao.HeroDao
import com.example.data.local.dao.OptimalStatsHeroDao
import com.example.data.local.dao.PathDao
import com.example.data.local.dao.RelicDao
import com.example.data.local.dao.WeaponDao
import com.example.data.local.entity.AbilityEntity
import com.example.data.local.entity.BuildDecorationEntity
import com.example.data.local.entity.BuildRelicEntity
import com.example.data.local.entity.BuildStatsEquipmentEntity
import com.example.data.local.entity.BuildWeaponEntity
import com.example.data.local.entity.DecorationEntity
import com.example.data.local.entity.EidolonEntity
import com.example.data.local.entity.ElementEntity
import com.example.data.local.entity.HeroEntity
import com.example.data.local.entity.OptimalStatsHeroEntity
import com.example.data.local.entity.PathEntity
import com.example.data.local.entity.RelicEntity
import com.example.data.local.entity.WeaponEntity

@Database(
    entities = [
        HeroEntity::class,
        PathEntity::class,
        ElementEntity::class,
        AbilityEntity::class,
        EidolonEntity::class,
        RelicEntity::class,
        DecorationEntity::class,
        OptimalStatsHeroEntity::class,
        BuildWeaponEntity::class,
        BuildRelicEntity::class,
        BuildDecorationEntity::class,
        BuildStatsEquipmentEntity::class,
        WeaponEntity::class
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
    abstract fun optimalStatsHeroDao(): OptimalStatsHeroDao
    abstract fun buildWeaponDao(): BuildWeaponDao
    abstract fun buildRelicDao(): BuildRelicDao
    abstract fun buildDecorationDao(): BuildDecorationDao
    abstract fun buildStatsEquipmentDao(): BuildStatsEquipmentDao
    abstract fun weaponsDao(): WeaponDao
}