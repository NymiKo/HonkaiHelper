package com.example.tanorami.activity.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.activity.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindViewModel(viewModel: MainViewModel): ViewModel
}