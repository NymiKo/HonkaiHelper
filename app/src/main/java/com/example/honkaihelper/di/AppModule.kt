package com.example.honkaihelper.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
interface AppModule {

    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideIoDispatcher() = Dispatchers.IO
    }

}