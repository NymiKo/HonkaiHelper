package com.example.tanorami.load_data.di

import com.example.tanorami.load_data.ui.LoadDataFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoadDataModule::class])
interface LoadDataComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoadDataComponent
    }

    fun inject(fragment: LoadDataFragment)
}