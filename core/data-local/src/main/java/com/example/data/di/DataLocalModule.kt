package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.RoomDatabaseApp
import com.example.data.db.contract.RoomContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataLocalModule {

    @Singleton
    @Provides
    fun provideRoom(context: Context): RoomDatabaseApp =
        Room.databaseBuilder(context, RoomDatabaseApp::class.java, RoomContract.databaseApp).build()

    @Provides
    fun providesHeroDao(database: RoomDatabaseApp) = database.heroDao()

    @Provides
    fun providesPathDao(database: RoomDatabaseApp) = database.pathDao()

    @Provides
    fun providesElementDao(database: RoomDatabaseApp) = database.elementDao()

    @Provides
    fun providesAbilityDao(database: RoomDatabaseApp) = database.abilityDao()

    @Provides
    fun providesEidolonDao(database: RoomDatabaseApp) = database.eidolonDao()

    @Provides
    fun providesRelicDao(database: RoomDatabaseApp) = database.relicDao()

    @Provides
    fun providesDecorationDao(database: RoomDatabaseApp) = database.decorationDao()

    @Provides
    fun providesOptimalStatsHeroDao(database: RoomDatabaseApp) = database.optimalStatsHeroDao()

    @Provides
    fun providesBuildWeaponDao(database: RoomDatabaseApp) = database.buildWeaponDao()

    @Provides
    fun providesBuildRelicDao(database: RoomDatabaseApp) = database.buildRelicDao()

    @Provides
    fun providesBuildDecorationDao(database: RoomDatabaseApp) = database.buildDecorationDao()

    @Provides
    fun providesBuildStatsEquipmentDao(database: RoomDatabaseApp) =
        database.buildStatsEquipmentDao()

    @Provides
    fun providesWeaponDao(database: RoomDatabaseApp) = database.weaponsDao()
}