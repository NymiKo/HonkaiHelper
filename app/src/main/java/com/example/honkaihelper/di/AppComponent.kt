package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.di.CreateTeamComponent
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.di.HeroesListComponent
import com.example.honkaihelper.login.data.LoginRepository
import com.example.honkaihelper.login.di.LoginComponent
import com.example.honkaihelper.profile.data.ProfileRepository
import com.example.honkaihelper.profile.di.ProfileComponent
import com.example.honkaihelper.registration.data.RegistrationRepository
import com.example.honkaihelper.registration.di.RegistrationComponent
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.di.TeamsListComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun heroesListComponent(): HeroesListComponent.Factory
    fun teamsListComponent(): TeamsListComponent.Factory
    fun createTeamComponent(): CreateTeamComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun registrationComponent(): RegistrationComponent.Factory
    fun profileComponent(): ProfileComponent.Factory

    val heroesListRepository: HeroesListRepository
    val teamsListRepository: TeamsListRepository
    val createTeamRepository: CreateTeamRepository
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
    val profileRepository: ProfileRepository
}