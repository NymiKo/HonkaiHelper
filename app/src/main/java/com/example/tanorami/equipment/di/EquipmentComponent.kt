package com.example.tanorami.equipment.di

import com.example.tanorami.equipment.EquipmentFragment
import dagger.Subcomponent

@Subcomponent(modules = [EquipmentModule::class])
interface EquipmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EquipmentComponent
    }

    fun inject(fragment: EquipmentFragment)
}