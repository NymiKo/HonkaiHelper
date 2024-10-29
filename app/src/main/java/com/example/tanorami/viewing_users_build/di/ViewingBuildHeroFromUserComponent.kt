package com.example.tanorami.viewing_users_build.di

import dagger.Subcomponent

@Subcomponent(modules = [ViewingBuildHeroFromUserModel::class])
interface ViewingBuildHeroFromUserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewingBuildHeroFromUserComponent
    }
}