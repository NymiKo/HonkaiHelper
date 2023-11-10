package com.example.honkaihelper.decoration.di

import com.example.honkaihelper.decoration.DecorationInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [DecorationInfoModule::class])
interface DecorationInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DecorationInfoComponent
    }

    fun inject(fragment: DecorationInfoFragment)
}