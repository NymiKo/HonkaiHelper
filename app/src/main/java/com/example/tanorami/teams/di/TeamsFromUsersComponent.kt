package com.example.tanorami.teams.di

import com.example.tanorami.teams.ui.TeamsListFragment
import dagger.Subcomponent

@Subcomponent(modules = [TeamsFromUsersModule::class])
interface TeamsFromUsersComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): TeamsFromUsersComponent
    }

    fun inject(fragment: TeamsListFragment)
}