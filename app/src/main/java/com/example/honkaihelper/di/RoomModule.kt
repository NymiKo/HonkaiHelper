package com.example.honkaihelper.di

import android.content.Context
import androidx.room.Room
import com.example.honkaihelper.data.local.RoomDatabaseApp
import com.example.honkaihelper.data.local.contract.RoomContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(context: Context): RoomDatabaseApp =
        Room.databaseBuilder(context, RoomDatabaseApp::class.java, RoomContract.databaseApp).build()

    @Provides
    fun bindHeroDao(database: RoomDatabaseApp) = database.heroDao()

    @Provides
    fun bindPathDao(database: RoomDatabaseApp) = database.pathDao()

    @Provides
    fun bindElementDao(database: RoomDatabaseApp) = database.elementDao()

    @Provides
    fun bindAbilityDao(database: RoomDatabaseApp) = database.abilityDao()

    @Provides
    fun bindEidolonDao(database: RoomDatabaseApp) = database.eidolonDao()

    @Provides
    fun bindRelicDao(database: RoomDatabaseApp) = database.relicDao()

    @Provides
    fun bindDecorationDao(database: RoomDatabaseApp) = database.decorationDao()

    @Provides
    fun bindOptimalStatsHeroDao(database: RoomDatabaseApp) = database.optimalStatsHeroDao()

    @Provides
    fun bindBuildWeaponDao(database: RoomDatabaseApp) = database.buildWeaponDao()

    @Provides
    fun bindBuildRelicDao(database: RoomDatabaseApp) = database.buildRelicDao()

    @Provides
    fun bindBuildDecorationDao(database: RoomDatabaseApp) = database.buildDecorationDao()

    @Provides
    fun bindBuildStatsEquipmentDao(database: RoomDatabaseApp) = database.buildStatsEquipmentDao()

    @Provides
    fun bindWeaponDao(database: RoomDatabaseApp) = database.weaponsDao()
}