package com.example.honkaihelper.relic.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.relic.RelicInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RelicInfoModule {
    @Binds
    @[IntoMap ViewModelKey(RelicInfoViewModel::class)]
    fun bindViewModel(viewModel: RelicInfoViewModel): ViewModel
}