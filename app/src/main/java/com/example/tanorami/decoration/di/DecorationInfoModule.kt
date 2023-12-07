package com.example.tanorami.decoration.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.decoration.DecorationInfoViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DecorationInfoModule {
    @Binds
    @[IntoMap ViewModelKey(DecorationInfoViewModel::class)]
    fun bindViewModel(viewModel: DecorationInfoViewModel): ViewModel
}