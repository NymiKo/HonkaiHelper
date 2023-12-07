package com.example.tanorami.di

import dagger.Module
import dagger.Provides
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