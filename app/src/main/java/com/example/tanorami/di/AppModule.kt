package com.example.tanorami.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
interface AppModule {

    companion object {
        @Provides
        @IODispatcher
        fun provideIoDispatcher() = Dispatchers.IO

        @Provides
        @MainDispatcher
        fun provideMainDispatcher() = Dispatchers.Main
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher