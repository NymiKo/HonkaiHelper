package com.example.honkaihelper.setupteam.di

import com.example.honkaihelper.setupteam.SetupTeamFragment
import dagger.Subcomponent

@Subcomponent(modules = [SetupTeamModule::class])
interface SetupTeamComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SetupTeamComponent
    }

    fun inject(fragment: SetupTeamFragment)
}