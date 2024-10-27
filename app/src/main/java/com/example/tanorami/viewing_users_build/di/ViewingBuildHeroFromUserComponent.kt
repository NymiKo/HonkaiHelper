package com.example.tanorami.viewing_users_build.di

import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewingBuildHeroFromUserModel::class])
interface ViewingBuildHeroFromUserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewingBuildHeroFromUserComponent
    }

    fun inject(fragment: ViewingBuildHeroFromUserFragment)
}