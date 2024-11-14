package com.example.tanorami.load_data.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.tanorami.load_data.presentation.LoadDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoadDataModule {
    @Binds
    @[IntoMap ViewModelKey(LoadDataViewModel::class)]
    fun bindViewModel(viewModel: LoadDataViewModel): ViewModel
}