package com.example.tanorami.weapons_list.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.weapons_list.presentation.WeaponsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WeaponsListModule {
    @Binds
    @[IntoMap ViewModelKey(WeaponsListViewModel::class)]
    fun bindViewModel(viewModel: WeaponsListViewModel): ViewModel
}