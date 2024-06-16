package com.example.tanorami.di

import android.content.Context
import com.example.tanorami.activity.data.MainRepository
import com.example.tanorami.activity.di.MainComponent
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepository
import com.example.tanorami.base_build_hero.di.BaseBuildHeroComponent
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.tanorami.info_about_hero.data.InfoAboutHeroRepository
import com.example.tanorami.info_about_hero.di.InfoAboutHeroComponent
import com.example.tanorami.builds_hero_from_users.di.BuildsHeroListComponent
import com.example.tanorami.change_nickname.data.ChangeNicknameRepository
import com.example.tanorami.change_nickname.di.ChangeNicknameComponent
import com.example.tanorami.create_build_hero.di.CreateBuildHeroComponent
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.di.CreateTeamComponent
import com.example.tanorami.decoration.data.DecorationInfoRepository
import com.example.tanorami.decoration.di.DecorationInfoComponent
import com.example.tanorami.equipment.di.EquipmentComponent
import com.example.tanorami.heroes.data.HeroesListRepository
import com.example.tanorami.heroes.di.HeroesListComponent
import com.example.tanorami.load_data.data.LoadDataRepository
import com.example.tanorami.load_data.di.LoadDataComponent
import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.login.di.LoginComponent
import com.example.tanorami.profile.domain.ProfileRepository
import com.example.tanorami.profile.di.ProfileComponent
import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.auth.registration.di.RegistrationComponent
import com.example.tanorami.relic.data.RelicInfoRepository
import com.example.tanorami.relic.di.RelicInfoComponent
import com.example.tanorami.send_feedback.data.SendFeedbackRepository
import com.example.tanorami.send_feedback.di.SendFeedbackComponent
import com.example.tanorami.settings.data.SettingsRepository
import com.example.tanorami.settings.di.SettingsComponent
import com.example.tanorami.teams.data.TeamsListRepository
import com.example.tanorami.teams.di.TeamsListComponent
import com.example.tanorami.viewing_users_build.data.ViewingUsersBuildRepository
import com.example.tanorami.viewing_users_build.di.ViewingUsersBuildComponent
import com.example.tanorami.weapon.data.WeaponInfoRepository
import com.example.tanorami.weapon.di.WeaponInfoComponent
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
        RoomModule::class,
        DataStoreModule::class,
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
    fun buildsHeroListComponent(): BuildsHeroListComponent.Factory
    fun createBuildHeroComponent(): CreateBuildHeroComponent.Factory
    fun infoAboutHeroComponent(): InfoAboutHeroComponent.Factory
    fun loadDataComponent(): LoadDataComponent.Factory
    fun baseBuildHeroComponent(): BaseBuildHeroComponent.Factory
    fun weaponInfoComponent(): WeaponInfoComponent.Factory
    fun relicInfoComponent(): RelicInfoComponent.Factory
    fun decorationInfoComponent(): DecorationInfoComponent.Factory
    fun viewingUsersBuildComponent(): ViewingUsersBuildComponent.Factory
    fun changeNicknameComponent(): ChangeNicknameComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
    fun sendFeedbackComponent(): SendFeedbackComponent.Factory

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
    val buildsHeroListRepository: BuildsHeroListRepository
    val viewingUsersBuildRepository: ViewingUsersBuildRepository
    val changeNicknameRepository: ChangeNicknameRepository
    val settingsRepository: SettingsRepository
    val sendFeedbackRepository: SendFeedbackRepository
}