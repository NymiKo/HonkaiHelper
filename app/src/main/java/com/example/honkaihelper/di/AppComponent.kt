package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.activity.MainActivity
import com.example.honkaihelper.createteam.di.CreateTeamComponent
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.di.HeroesListComponent
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.di.TeamsListComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(modules = [
    ViewModelFactoryModule::class,
    RepositoryModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
    fun heroesListComponent(): HeroesListComponent.Factory
    fun teamsListComponent(): TeamsListComponent.Factory
    fun createTeamComponent(): CreateTeamComponent.Factory

    val heroesListRepository: HeroesListRepository
    val teamsListRepository: TeamsListRepository
}