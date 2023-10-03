package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.heroes.data.HeroesListRepository
import dagger.BindsInstance
import dagger.Component

@Component
interface TestAppComponent: AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }

    override val heroesListRepository: HeroesListRepository
}