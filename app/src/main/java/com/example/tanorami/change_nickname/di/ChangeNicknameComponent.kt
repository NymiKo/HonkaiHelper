package com.example.tanorami.change_nickname.di

import com.example.tanorami.change_nickname.ChangeNicknameFragment
import dagger.Subcomponent

@Subcomponent(modules = [ChangeNicknameModule::class])
interface ChangeNicknameComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChangeNicknameComponent
    }

    fun inject(fragment: ChangeNicknameFragment)
}