package com.example.tanorami.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class, ViewModelFactoryModule::class, AppModule::class])
interface TestAppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }
}