package com.example.tanorami.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tanorami.core.database.dao.AbilityDao
import com.example.tanorami.core.database.dao.BuildDecorationDao
import com.example.tanorami.core.database.dao.BuildRelicDao
import com.example.tanorami.core.database.dao.BuildStatsEquipmentDao
import com.example.tanorami.core.database.dao.BuildWeaponDao
import com.example.tanorami.core.database.dao.DecorationDao
import com.example.tanorami.core.database.dao.EidolonDao
import com.example.tanorami.core.database.dao.ElementDao
import com.example.tanorami.core.database.dao.HeroDao
import com.example.tanorami.core.database.dao.OptimalStatsHeroDao
import com.example.tanorami.core.database.dao.PathDao
import com.example.tanorami.core.database.dao.RelicDao
import com.example.tanorami.core.database.dao.WeaponDao
import com.example.tanorami.core.database.entity.AbilityEntity
import com.example.tanorami.core.database.entity.BuildDecorationEntity
import com.example.tanorami.core.database.entity.BuildRelicEntity
import com.example.tanorami.core.database.entity.BuildStatsEquipmentEntity
import com.example.tanorami.core.database.entity.BuildWeaponEntity
import com.example.tanorami.core.database.entity.DecorationEntity
import com.example.tanorami.core.database.entity.EidolonEntity
import com.example.tanorami.core.database.entity.ElementEntity
import com.example.tanorami.core.database.entity.HeroEntity
import com.example.tanorami.core.database.entity.OptimalStatsHeroEntity
import com.example.tanorami.core.database.entity.PathEntity
import com.example.tanorami.core.database.entity.RelicEntity
import com.example.tanorami.core.database.entity.WeaponEntity

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