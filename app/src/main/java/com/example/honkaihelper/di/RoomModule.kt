package com.example.honkaihelper.di

import android.content.Context
import androidx.room.Room
import com.example.honkaihelper.data.local.RoomDatabaseApp
import com.example.honkaihelper.data.local.contract.RoomContract
import dagger.Binds
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
}