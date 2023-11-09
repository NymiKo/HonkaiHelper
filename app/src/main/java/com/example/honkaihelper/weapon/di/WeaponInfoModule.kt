package com.example.honkaihelper.weapon.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.weapon.WeaponInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WeaponInfoModule {
    @Binds
    @[IntoMap ViewModelKey(WeaponInfoViewModel::class)]
    fun bindViewModel(viewModel: WeaponInfoViewModel): ViewModel
}