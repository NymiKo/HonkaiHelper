package com.example.tanorami.info_about_weapon.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.info_about_weapon.presentation.InfoAboutWeaponViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface InfoAboutWeaponModule {
    @Binds
    @[IntoMap ViewModelKey(InfoAboutWeaponViewModel::class)]
    fun bindViewModel(viewModel: InfoAboutWeaponViewModel): ViewModel
}