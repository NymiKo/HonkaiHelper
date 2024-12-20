package com.example.tanorami.load_data.di

import dagger.Subcomponent

@Subcomponent(modules = [LoadDataModule::class])
interface LoadDataComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoadDataComponent
    }
}