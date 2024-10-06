package com.example.tanorami.info_about_decoration.di

import com.example.tanorami.info_about_decoration.ui.InfoAboutDecorationFragment
import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutDecorationModule::class])
interface InfoAboutDecorationComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutDecorationComponent
    }

    fun inject(fragment: InfoAboutDecorationFragment)
}