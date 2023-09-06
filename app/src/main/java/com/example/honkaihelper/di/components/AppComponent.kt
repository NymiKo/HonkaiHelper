package com.example.honkaihelper.di.components

import com.example.honkaihelper.activity.MainActivity
import dagger.Component

@Component
interface AppComponent {

    fun inject(activity: MainActivity)

}