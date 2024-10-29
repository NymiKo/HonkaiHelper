package com.example.tanorami.info_about_decoration.di

import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutDecorationModule::class])
interface InfoAboutDecorationComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutDecorationComponent
    }
}