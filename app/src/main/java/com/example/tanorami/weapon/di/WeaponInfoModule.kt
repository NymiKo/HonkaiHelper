package com.example.tanorami.weapon.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.weapon.WeaponInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WeaponInfoModule {
    @Binds
    @[IntoMap ViewModelKey(WeaponInfoViewModel::class)]
    fun bindViewModel(viewModel: WeaponInfoViewModel): ViewModel
}