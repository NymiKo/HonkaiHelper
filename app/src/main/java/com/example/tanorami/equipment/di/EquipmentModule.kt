package com.example.tanorami.equipment.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.equipment.EquipmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EquipmentModule {

    @Binds
    @[IntoMap ViewModelKey(EquipmentViewModel::class)]
    fun bindViewModel(viewModel: EquipmentViewModel): ViewModel
}