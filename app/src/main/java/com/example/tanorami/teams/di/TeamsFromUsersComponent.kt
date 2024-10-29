package com.example.tanorami.teams.di

import dagger.Subcomponent

@Subcomponent(modules = [TeamsFromUsersModule::class])
interface TeamsFromUsersComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): TeamsFromUsersComponent
    }
}