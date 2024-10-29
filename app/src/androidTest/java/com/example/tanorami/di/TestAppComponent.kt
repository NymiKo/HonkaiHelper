package com.example.tanorami.di

import android.content.Context
import com.example.tanorami.core.di.AppModule
import com.example.tanorami.core.di.ViewModelFactoryModule
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