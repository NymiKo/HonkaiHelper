package com.example.tanorami.info_about_relic.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.info_about_relic.presentation.RelicInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RelicInfoModule {
    @Binds
    @[IntoMap ViewModelKey(RelicInfoViewModel::class)]
    fun bindViewModel(viewModel: RelicInfoViewModel): ViewModel
}