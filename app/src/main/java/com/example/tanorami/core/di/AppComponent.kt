package com.example.tanorami.core.di

import android.content.Context
import com.example.tanorami.activity.di.MainComponent
import com.example.tanorami.auth.login.di.LoginModule
import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.auth.registration.di.RegistrationModule
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepository
import com.example.tanorami.base_build_hero.di.BaseBuildHeroModule
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.tanorami.builds_hero_from_users.di.BuildsHeroFromUsersModule
import com.example.tanorami.change_nickname.data.ChangeNicknameRepository
import com.example.tanorami.change_nickname.di.ChangeNicknameModule
import com.example.tanorami.create_build_hero.di.CreateBuildHeroModule
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.di.CreateTeamModule
import com.example.tanorami.heroes.data.HeroesListRepository
import com.example.tanorami.heroes.di.HeroesListModule
import com.example.tanorami.info_about_decoration.data.InfoAboutDecorationRepository
import com.example.tanorami.info_about_decoration.di.InfoAboutDecorationModule
import com.example.tanorami.info_about_hero.data.InfoAboutHeroRepository
import com.example.tanorami.info_about_hero.di.InfoAboutHeroModule
import com.example.tanorami.info_about_relic.data.InfoAboutRelicRepository
import com.example.tanorami.info_about_relic.di.InfoAboutRelicModule
import com.example.tanorami.info_about_weapon.data.InfoAboutWeaponRepository
import com.example.tanorami.info_about_weapon.di.InfoAboutWeaponModule
import com.example.tanorami.load_data.data.LoadDataRepository
import com.example.tanorami.load_data.di.LoadDataModule
import com.example.tanorami.main.data.MainScreenRepositoryImpl
import com.example.tanorami.main.di.MainScreenModule
import com.example.tanorami.profile.di.ProfileModule
import com.example.tanorami.profile.domain.ProfileRepository
import com.example.tanorami.send_feedback.data.SendFeedbackRepository
import com.example.tanorami.send_feedback.di.SendFeedbackModule
import com.example.tanorami.settings.data.SettingsRepository
import com.example.tanorami.settings.di.SettingsModule
import com.example.tanorami.teams.data.TeamsFromUsersRepository
import com.example.tanorami.teams.di.TeamsFromUsersModule
import com.example.tanorami.teams_and_builds.data.TeamsAndBuildsRepositoryImpl
import com.example.tanorami.teams_and_builds.di.TeamsAndBuildsModule
import com.example.tanorami.viewing_users_build.data.ViewingBuildHeroFromUserRepository
import com.example.tanorami.viewing_users_build.di.ViewingBuildHeroFromUserModel
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
        ProfileModule::class,
        HeroesListModule::class,
        BuildsHeroFromUsersModule::class,
        TeamsFromUsersModule::class,
        InfoAboutHeroModule::class,
        LoadDataModule::class,
        MainScreenModule::class,
        TeamsAndBuildsModule::class,
        SettingsModule::class,
        BaseBuildHeroModule::class,
        ViewingBuildHeroFromUserModel::class,
        InfoAboutWeaponModule::class,
        InfoAboutRelicModule::class,
        InfoAboutDecorationModule::class,
        CreateBuildHeroModule::class,
        CreateTeamModule::class,
        ChangeNicknameModule::class,
        LoginModule::class,
        RegistrationModule::class,
        SendFeedbackModule::class,
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory

    val heroesListRepository: HeroesListRepository
    val teamsFromUsersRepository: TeamsFromUsersRepository
    val createTeamRepository: CreateTeamRepository
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
    val profileRepository: ProfileRepository
    val infoAboutHeroRepository: InfoAboutHeroRepository
    val loadDataRepository: LoadDataRepository
    val baseBuildHeroRepository: BaseBuildHeroRepository
    val infoAboutWeaponRepository: InfoAboutWeaponRepository
    val infoAboutRelicRepository: InfoAboutRelicRepository
    val infoAboutDecorationRepository: InfoAboutDecorationRepository
    val buildsHeroListRepository: BuildsHeroListRepository
    val viewingBuildHeroFromUserRepository: ViewingBuildHeroFromUserRepository
    val changeNicknameRepository: ChangeNicknameRepository
    val settingsRepository: SettingsRepository
    val sendFeedbackRepository: SendFeedbackRepository
    val mainScreenRepository: MainScreenRepositoryImpl
    val teamsAndBuildsRepository: TeamsAndBuildsRepositoryImpl
}