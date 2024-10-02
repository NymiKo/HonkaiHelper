package com.example.tanorami.createteam.di

import com.example.tanorami.createteam.ui.CreateTeamFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreateTeamModule::class])
interface CreateTeamComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateTeamComponent
    }
    fun inject(fragment: CreateTeamFragment)
}