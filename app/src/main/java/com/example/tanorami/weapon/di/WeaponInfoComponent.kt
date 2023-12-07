package com.example.tanorami.weapon.di

import com.example.tanorami.weapon.WeaponInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [WeaponInfoModule::class])
interface WeaponInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WeaponInfoComponent
    }

    fun inject(fragment: WeaponInfoFragment)
}