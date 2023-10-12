package com.example.honkaihelper.equipment.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.equipment.EquipmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EquipmentModule {

    @Binds
    @[IntoMap ViewModelKey(EquipmentViewModel::class)]
    fun bindViewModel(viewModel: EquipmentViewModel): ViewModel
}