package com.example.tanorami

import android.app.Application
import com.example.heroes_list.heroes.di.HeroesListComponentDependencies
import com.example.heroes_list.heroes.di.HeroesListComponentDependenciesProvider
import com.example.tanorami.di.AppComponent
import com.example.tanorami.di.DaggerAppComponent

open class App : Application(), HeroesListComponentDependenciesProvider {

    open val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun getHeroesListComponentDependencies(): HeroesListComponentDependencies {
        return appComponent
    }
}