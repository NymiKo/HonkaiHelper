package com.example.tanorami.viewing_users_build.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.viewing_users_build.ViewingUsersBuildViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewingUsersBuildModule {

    @Binds
    @[IntoMap ViewModelKey(ViewingUsersBuildViewModel::class)]
    fun bindViewModel(viewModel: ViewingUsersBuildViewModel): ViewModel
}