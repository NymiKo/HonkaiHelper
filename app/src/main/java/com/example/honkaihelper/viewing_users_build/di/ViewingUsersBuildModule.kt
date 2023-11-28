package com.example.honkaihelper.viewing_users_build.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.viewing_users_build.ViewingUsersBuildViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewingUsersBuildModule {

    @Binds
    @[IntoMap ViewModelKey(ViewingUsersBuildViewModel::class)]
    fun bindViewModel(viewModel: ViewingUsersBuildViewModel): ViewModel
}