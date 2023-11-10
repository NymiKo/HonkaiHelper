package com.example.honkaihelper.decoration.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.decoration.DecorationInfoViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DecorationInfoModule {
    @Binds
    @[IntoMap ViewModelKey(DecorationInfoViewModel::class)]
    fun bindViewModel(viewModel: DecorationInfoViewModel): ViewModel
}