package com.example.tanorami.createteam.di

import dagger.Subcomponent

@Subcomponent(modules = [CreateTeamModule::class])
interface CreateTeamComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateTeamComponent
    }
}