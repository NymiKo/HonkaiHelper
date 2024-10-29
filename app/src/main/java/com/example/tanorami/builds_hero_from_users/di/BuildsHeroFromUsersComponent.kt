package com.example.tanorami.builds_hero_from_users.di

import dagger.Subcomponent

@Subcomponent(modules = [BuildsHeroFromUsersModule::class])
interface BuildsHeroFromUsersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BuildsHeroFromUsersComponent
    }
}