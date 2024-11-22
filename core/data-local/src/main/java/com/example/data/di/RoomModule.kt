package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.RoomDatabaseApp
import com.example.data.db.contract.RoomContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(context: Context): RoomDatabaseApp =
        Room.databaseBuilder(context, RoomDatabaseApp::class.java, RoomContract.databaseApp).build()

    @Provides
    @Singleton
    fun providesHeroDao(database: RoomDatabaseApp) = database.heroDao()

    @Provides
    @Singleton
    fun providesPathDao(database: RoomDatabaseApp) = database.pathDao()

    @Provides
    @Singleton
    fun providesElementDao(database: RoomDatabaseApp) = database.elementDao()

    @Provides
    @Singleton
    fun providesAbilityDao(database: RoomDatabaseApp) = database.abilityDao()

    @Provides
    @Singleton
    fun providesEidolonDao(database: RoomDatabaseApp) = database.eidolonDao()

    @Provides
    @Singleton
    fun providesRelicDao(database: RoomDatabaseApp) = database.relicDao()

    @Provides
    @Singleton
    fun providesDecorationDao(database: RoomDatabaseApp) = database.decorationDao()

    @Provides
    @Singleton
    fun providesOptimalStatsHeroDao(database: RoomDatabaseApp) = database.optimalStatsHeroDao()

    @Provides
    @Singleton
    fun providesBuildWeaponDao(database: RoomDatabaseApp) = database.buildWeaponDao()

    @Provides
    @Singleton
    fun providesBuildRelicDao(database: RoomDatabaseApp) = database.buildRelicDao()

    @Provides
    @Singleton
    fun providesBuildDecorationDao(database: RoomDatabaseApp) = database.buildDecorationDao()

    @Provides
    @Singleton
    fun providesBuildStatsEquipmentDao(database: RoomDatabaseApp) =
        database.buildStatsEquipmentDao()

    @Provides
    @Singleton
    fun providesWeaponDao(database: RoomDatabaseApp) = database.weaponsDao()
}