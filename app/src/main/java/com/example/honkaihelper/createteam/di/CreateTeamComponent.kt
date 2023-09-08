package com.example.honkaihelper.createteam.di

import com.example.honkaihelper.createteam.CreateTeamFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreateTeamModule::class])
interface CreateTeamComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateTeamComponent
    }
    fun inject(fragment: CreateTeamFragment)
}