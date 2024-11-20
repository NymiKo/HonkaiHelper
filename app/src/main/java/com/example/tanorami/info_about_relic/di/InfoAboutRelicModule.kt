package com.example.tanorami.info_about_relic.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.info_about_relic.presentation.InfoAboutRelicViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface InfoAboutRelicModule {
    @Binds
    @[IntoMap ViewModelKey(InfoAboutRelicViewModel::class)]
    fun bindViewModel(viewModel: InfoAboutRelicViewModel): ViewModel
}