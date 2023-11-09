package com.example.honkaihelper.weapon.di

import com.example.honkaihelper.weapon.WeaponInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [WeaponInfoModule::class])
interface WeaponInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WeaponInfoComponent
    }

    fun inject(fragment: WeaponInfoFragment)
}