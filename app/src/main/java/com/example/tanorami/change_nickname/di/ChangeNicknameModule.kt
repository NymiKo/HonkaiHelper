package com.example.tanorami.change_nickname.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.change_nickname.presentation.ChangeNicknameViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ChangeNicknameModule {
    @Binds
    @[IntoMap ViewModelKey(ChangeNicknameViewModel::class)]
    fun bindViewModel(viewModel: ChangeNicknameViewModel): ViewModel
}