package com.example.tanorami.di

import android.content.Context
import com.example.core.di.ViewModelFactoryModule
import com.example.domain.di.DispatcherModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class, ViewModelFactoryModule::class, DispatcherModule::class])
interface TestAppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }
}