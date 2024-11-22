package com.example.domain.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
interface DispatcherModule {
    companion object {
        @Provides
        @IODispatcher
        fun provideIoDispatcher() = Dispatchers.IO

        @Provides
        @DefaultDispatcher
        fun provideDefaultDispatcher() = Dispatchers.Default

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
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher