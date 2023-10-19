package com.example.honkaihelper.load_data.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.load_data.LoadDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoadDataModule {
    @Binds
    @[IntoMap ViewModelKey(LoadDataViewModel::class)]
    fun bindViewModel(viewModel: LoadDataViewModel): ViewModel
}