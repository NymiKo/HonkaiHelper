package com.example.honkaihelper.viewing_users_build.di

import com.example.honkaihelper.viewing_users_build.ViewingUsersBuildFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewingUsersBuildModule::class])
interface ViewingUsersBuildComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewingUsersBuildComponent
    }

    fun inject(fragment: ViewingUsersBuildFragment)
}