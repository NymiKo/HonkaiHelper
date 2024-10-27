package com.example.tanorami.builds_hero_from_users.di

import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersFragment
import dagger.Subcomponent

@Subcomponent(modules = [BuildsHeroFromUsersModule::class])
interface BuildsHeroFromUsersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BuildsHeroFromUsersComponent
    }

    fun inject(fragment: BuildsHeroFromUsersFragment)
}