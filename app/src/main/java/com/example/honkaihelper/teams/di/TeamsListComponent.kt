package com.example.honkaihelper.teams.di

import com.example.honkaihelper.teams.TeamsListFragment
import dagger.Subcomponent

@Subcomponent(modules = [TeamsListModule::class])
interface TeamsListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): TeamsListComponent
    }

    fun inject(fragment: TeamsListFragment)
}