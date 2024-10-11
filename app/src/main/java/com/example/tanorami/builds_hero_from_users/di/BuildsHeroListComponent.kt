package com.example.tanorami.builds_hero_from_users.di

import com.example.tanorami.builds_hero_from_users.ui.BuildsHeroFromUsersFragment
import dagger.Subcomponent

@Subcomponent(modules = [BuildsHeroListModule::class])
interface BuildsHeroListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BuildsHeroListComponent
    }

    fun inject(fragment: BuildsHeroFromUsersFragment)
}