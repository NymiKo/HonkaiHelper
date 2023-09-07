package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.activity.MainActivity
import com.example.honkaihelper.heroes.di.HeroesListComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(modules = [
    ViewModelFactoryModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
    fun heroesListComponent(): HeroesListComponent.Factory

}