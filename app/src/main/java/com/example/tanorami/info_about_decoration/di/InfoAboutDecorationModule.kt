package com.example.tanorami.info_about_decoration.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.info_about_decoration.presentation.InfoAboutDecorationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface InfoAboutDecorationModule {
    @Binds
    @[IntoMap ViewModelKey(InfoAboutDecorationViewModel::class)]
    fun bindViewModel(viewModel: InfoAboutDecorationViewModel): ViewModel
}