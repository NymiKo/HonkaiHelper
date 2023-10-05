package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.heroes.data.HeroesListRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class, ViewModelFactoryModule::class, AppModule::class])
interface TestAppComponent: AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }
}