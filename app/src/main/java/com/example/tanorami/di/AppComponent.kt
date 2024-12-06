package com.example.tanorami.di

import android.content.Context
import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero_build.HeroBuildsFromUsersRemoteDataSource
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.team.TeamsFromUsersRemoteDataSource
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.domain.data_store.AppDataStore
import com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase
import com.example.heroes_list.heroes.di.HeroesListComponentDependencies
import com.example.tanorami.activity.MainActivity
import com.example.teams_and_builds.di.TeamsAndBuildsComponentDependencies
import com.example.weapons_list.di.WeaponsListComponentDependencies
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class],
)
interface AppComponent : HeroesListComponentDependencies, WeaponsListComponentDependencies,
    TeamsAndBuildsComponentDependencies {

    override val getUseCase: GetHeroesListWithBaseInfoUseCase
    override val getAppDataStore: AppDataStore

    override val getWeaponLocalDataSource: WeaponLocalDataSource
    override val getIoDispatcher: CoroutineDispatcher

    override val getHeroBuildsFromUsersRemoteDataSource: HeroBuildsFromUsersRemoteDataSource
    override val getTeamsFromUsersRemoteDataSource: TeamsFromUsersRemoteDataSource
    override val getHeroLocalDataSource: HeroLocalDataSource
    override val getRelicLocalDataSource: RelicLocalDataSource
    override val getDecorationLocalDataSource: DecorationLocalDataSource

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): AppComponent
    }

    fun inject(activity: MainActivity)
}