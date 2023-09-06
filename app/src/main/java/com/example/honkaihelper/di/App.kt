package com.example.honkaihelper.di

import android.app.Application
import com.example.honkaihelper.di.components.DaggerAppComponent

class App: Application() {

    val appComponent = DaggerAppComponent.create()

}