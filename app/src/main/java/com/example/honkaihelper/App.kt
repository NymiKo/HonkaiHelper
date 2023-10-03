package com.example.honkaihelper

import android.app.Application
import com.example.honkaihelper.di.AppComponent
import com.example.honkaihelper.di.DaggerAppComponent

open class App: Application() {

    open val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}