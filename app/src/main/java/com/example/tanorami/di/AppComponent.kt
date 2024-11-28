package com.example.tanorami.di

import android.content.Context
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.domain.data_store.AppDataStore
import com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase
import com.example.heroes_list.heroes.di.HeroesListComponentDependencies
import com.example.tanorami.activity.MainActivity
import com.example.weapons_list.di.WeaponsListComponentDependencies
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class],
)
interface AppComponent : HeroesListComponentDependencies, WeaponsListComponentDependencies {

    override val getUseCase: GetHeroesListWithBaseInfoUseCase
    override val getAppDataStore: AppDataStore

    override val getWeaponLocalDataSource: WeaponLocalDataSource
    override val getIoDispatcher: CoroutineDispatcher

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): AppComponent
    }

    fun inject(activity: MainActivity)
}