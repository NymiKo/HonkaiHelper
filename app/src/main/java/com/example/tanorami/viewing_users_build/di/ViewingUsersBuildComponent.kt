package com.example.tanorami.viewing_users_build.di

import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewingUsersBuildModule::class])
interface ViewingUsersBuildComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewingUsersBuildComponent
    }

    fun inject(fragment: ViewingBuildHeroFromUserFragment)
}