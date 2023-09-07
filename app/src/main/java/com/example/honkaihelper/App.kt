package com.example.honkaihelper

import android.app.Application
import com.example.honkaihelper.di.AppComponent
import com.example.honkaihelper.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}