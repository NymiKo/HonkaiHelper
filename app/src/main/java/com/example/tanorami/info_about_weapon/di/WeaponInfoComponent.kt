package com.example.tanorami.info_about_weapon.di

import com.example.tanorami.info_about_weapon.ui.WeaponInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [WeaponInfoModule::class])
interface WeaponInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WeaponInfoComponent
    }

    fun inject(fragment: WeaponInfoFragment)
}