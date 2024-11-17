package com.example.core.di

import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CoreScope

@CoreScope
@Component(
    modules = [
        AppModule::class,
        DataStoreModule::class,
        RepositoryModule::class,
        LocalDataSourceModule::class,
        RoomModule::class,
        UseCaseModule::class,
    ],
)
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }
}