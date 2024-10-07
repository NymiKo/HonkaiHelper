package com.example.tanorami.info_about_weapon.di

import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponFragment
import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutWeaponModule::class])
interface InfoAboutWeaponComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutWeaponComponent
    }

    fun inject(fragment: InfoAboutWeaponFragment)
}