package com.example.tanorami.info_about_decoration.di

import com.example.tanorami.info_about_decoration.DecorationInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [DecorationInfoModule::class])
interface DecorationInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DecorationInfoComponent
    }

    fun inject(fragment: DecorationInfoFragment)
}