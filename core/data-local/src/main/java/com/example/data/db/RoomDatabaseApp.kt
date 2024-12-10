package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.AbilityDao
import com.example.data.db.dao.AdditionalStatDao
import com.example.data.db.dao.BuildDecorationDao
import com.example.data.db.dao.BuildRelicDao
import com.example.data.db.dao.BuildStatsEquipmentDao
import com.example.data.db.dao.BuildWeaponDao
import com.example.data.db.dao.DecorationDao
import com.example.data.db.dao.EidolonDao
import com.example.data.db.dao.ElementDao
import com.example.data.db.dao.HeroDao
import com.example.data.db.dao.OptimalStatsHeroDao
import com.example.data.db.dao.PathDao
import com.example.data.db.dao.RelicDao
import com.example.data.db.dao.StatDao
import com.example.data.db.dao.WeaponDao
import com.example.data.db.entity.AbilityEntity
import com.example.data.db.entity.AdditionalStatEntity
import com.example.data.db.entity.BuildDecorationEntity
import com.example.data.db.entity.BuildRelicEntity
import com.example.data.db.entity.BuildStatsEquipmentEntity
import com.example.data.db.entity.BuildWeaponEntity
import com.example.data.db.entity.DecorationEntity
import com.example.data.db.entity.EidolonEntity
import com.example.data.db.entity.ElementEntity
import com.example.data.db.entity.HeroEntity
import com.example.data.db.entity.OptimalStatHeroEntity
import com.example.data.db.entity.PathEntity
import com.example.data.db.entity.RelicEntity
import com.example.data.db.entity.StatEntity
import com.example.data.db.entity.WeaponEntity

@Database(
    entities = [
        HeroEntity::class,
        PathEntity::class,
        ElementEntity::class,
        AbilityEntity::class,
        EidolonEntity::class,
        RelicEntity::class,
        DecorationEntity::class,
        OptimalStatHeroEntity::class,
        BuildWeaponEntity::class,
        BuildRelicEntity::class,
        BuildDecorationEntity::class,
        BuildStatsEquipmentEntity::class,
        WeaponEntity::class,
        StatEntity::class,
        AdditionalStatEntity::class,
    ],
    version = 2,
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
    abstract fun statDao(): StatDao
    abstract fun additionalStat(): AdditionalStatDao
}