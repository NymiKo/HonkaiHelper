package com.example.tanorami.decoration.di

import com.example.tanorami.decoration.DecorationInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [DecorationInfoModule::class])
interface DecorationInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DecorationInfoComponent
    }

    fun inject(fragment: DecorationInfoFragment)
}