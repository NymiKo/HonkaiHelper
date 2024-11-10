package com.example.tanorami.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tanorami.core.data.local.dao.AbilityDao
import com.example.tanorami.core.data.local.dao.BuildDecorationDao
import com.example.tanorami.core.data.local.dao.BuildRelicDao
import com.example.tanorami.core.data.local.dao.BuildStatsEquipmentDao
import com.example.tanorami.core.data.local.dao.BuildWeaponDao
import com.example.tanorami.core.data.local.dao.DecorationDao
import com.example.tanorami.core.data.local.dao.EidolonDao
import com.example.tanorami.core.data.local.dao.ElementDao
import com.example.tanorami.core.data.local.dao.HeroDao
import com.example.tanorami.core.data.local.dao.OptimalStatsHeroDao
import com.example.tanorami.core.data.local.dao.PathDao
import com.example.tanorami.core.data.local.dao.RelicDao
import com.example.tanorami.core.data.local.dao.WeaponDao
import com.example.tanorami.core.data.local.entity.AbilityEntity
import com.example.tanorami.core.data.local.entity.BuildDecorationEntity
import com.example.tanorami.core.data.local.entity.BuildRelicEntity
import com.example.tanorami.core.data.local.entity.BuildStatsEquipmentEntity
import com.example.tanorami.core.data.local.entity.BuildWeaponEntity
import com.example.tanorami.core.data.local.entity.DecorationEntity
import com.example.tanorami.core.data.local.entity.EidolonEntity
import com.example.tanorami.core.data.local.entity.ElementEntity
import com.example.tanorami.core.data.local.entity.HeroEntity
import com.example.tanorami.core.data.local.entity.OptimalStatsHeroEntity
import com.example.tanorami.core.data.local.entity.PathEntity
import com.example.tanorami.core.data.local.entity.RelicEntity
import com.example.tanorami.core.data.local.entity.WeaponEntity

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