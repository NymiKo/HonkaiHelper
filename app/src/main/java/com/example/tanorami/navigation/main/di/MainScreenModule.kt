package com.example.tanorami.navigation.main.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.navigation.main.presentation.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainScreenModule {
    @Binds
    @[IntoMap ViewModelKey(MainScreenViewModel::class)]
    fun bindViewModel(viewModel: MainScreenViewModel): ViewModel
}