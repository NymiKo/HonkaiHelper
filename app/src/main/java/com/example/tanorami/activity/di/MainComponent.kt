package com.example.tanorami.activity.di

import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }


}