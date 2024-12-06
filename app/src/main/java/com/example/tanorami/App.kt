package com.example.tanorami

import android.app.Application
import com.example.heroes_list.heroes.di.HeroesListComponentDependencies
import com.example.heroes_list.heroes.di.HeroesListComponentDependenciesProvider
import com.example.tanorami.di.AppComponent
import com.example.tanorami.di.DaggerAppComponent
import com.example.teams_and_builds.di.TeamsAndBuildsComponentDependencies
import com.example.teams_and_builds.di.TeamsAndBuildsComponentDependenciesProvider
import com.example.weapons_list.di.WeaponsListComponentDependencies
import com.example.weapons_list.di.WeaponsListComponentDependenciesProvider

open class App : Application(), HeroesListComponentDependenciesProvider,
    WeaponsListComponentDependenciesProvider, TeamsAndBuildsComponentDependenciesProvider {

    open val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun getHeroesListComponentDependencies(): HeroesListComponentDependencies {
        return appComponent
    }

    override fun getWeaponsListComponentDependencies(): WeaponsListComponentDependencies {
        return appComponent
    }

    override fun getTeamsAndBuildsComponentDependencies(): TeamsAndBuildsComponentDependencies {
        return appComponent
    }
}