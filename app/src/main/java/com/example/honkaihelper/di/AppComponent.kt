package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.activity.data.MainRepository
import com.example.honkaihelper.activity.di.MainComponent
import com.example.honkaihelper.base_build_hero.data.BaseBuildHeroRepository
import com.example.honkaihelper.base_build_hero.di.BaseBuildHeroComponent
import com.example.honkaihelper.info_about_hero.data.InfoAboutHeroRepository
import com.example.honkaihelper.info_about_hero.di.InfoAboutHeroComponent
import com.example.honkaihelper.builds_hero.di.BuildsHeroListComponent
import com.example.honkaihelper.create_build_hero.di.CreateBuildHeroComponent
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.di.CreateTeamComponent
import com.example.honkaihelper.decoration.data.DecorationInfoRepository
import com.example.honkaihelper.decoration.di.DecorationInfoComponent
import com.example.honkaihelper.equipment.di.EquipmentComponent
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.di.HeroesListComponent
import com.example.honkaihelper.load_data.data.LoadDataRepository
import com.example.honkaihelper.load_data.di.LoadDataComponent
import com.example.honkaihelper.login.data.LoginRepository
import com.example.honkaihelper.login.di.LoginComponent
import com.example.honkaihelper.profile.data.ProfileRepository
import com.example.honkaihelper.profile.di.ProfileComponent
import com.example.honkaihelper.registration.data.RegistrationRepository
import com.example.honkaihelper.registration.di.RegistrationComponent
import com.example.honkaihelper.relic.data.RelicInfoRepository
import com.example.honkaihelper.relic.di.RelicInfoComponent
import com.example.honkaihelper.setupteam.di.SetupTeamComponent
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.di.TeamsListComponent
import com.example.honkaihelper.weapon.data.WeaponInfoRepository
import com.example.honkaihelper.weapon.di.WeaponInfoComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        RoomModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun heroesListComponent(): HeroesListComponent.Factory
    fun teamsListComponent(): TeamsListComponent.Factory
    fun createTeamComponent(): CreateTeamComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun registrationComponent(): RegistrationComponent.Factory
    fun profileComponent(): ProfileComponent.Factory
    fun equipmentComponent(): EquipmentComponent.Factory
    fun setupTeamComponent(): SetupTeamComponent.Factory
    fun buildsHeroListComponent(): BuildsHeroListComponent.Factory
    fun createBuildHeroComponent(): CreateBuildHeroComponent.Factory
    fun infoAboutHeroComponent(): InfoAboutHeroComponent.Factory
    fun loadDataComponent(): LoadDataComponent.Factory
    fun baseBuildHeroComponent(): BaseBuildHeroComponent.Factory
    fun weaponInfoComponent(): WeaponInfoComponent.Factory
    fun relicInfoComponent(): RelicInfoComponent.Factory
    fun decorationInfoComponent(): DecorationInfoComponent.Factory

    val heroesListRepository: HeroesListRepository
    val teamsListRepository: TeamsListRepository
    val createTeamRepository: CreateTeamRepository
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
    val profileRepository: ProfileRepository
    val infoAboutHeroRepository: InfoAboutHeroRepository
    val loadDataRepository: LoadDataRepository
    val mainRepository: MainRepository
    val baseBuildHeroRepository: BaseBuildHeroRepository
    val weaponInfoRepository: WeaponInfoRepository
    val relicInfoRepository: RelicInfoRepository
    val decorationInfoRepository: DecorationInfoRepository
}