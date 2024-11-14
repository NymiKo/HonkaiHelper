package com.example.tanorami.main.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.tanorami.main.presentation.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainScreenModule {
    @Binds
    @[IntoMap ViewModelKey(MainScreenViewModel::class)]
    fun bindViewModel(viewModel: MainScreenViewModel): ViewModel
}