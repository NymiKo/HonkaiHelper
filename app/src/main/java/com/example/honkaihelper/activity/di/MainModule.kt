package com.example.honkaihelper.activity.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.activity.MainViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindViewModel(viewModel: MainViewModel): ViewModel
}