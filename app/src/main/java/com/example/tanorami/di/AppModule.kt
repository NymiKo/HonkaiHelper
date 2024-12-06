package com.example.tanorami.di

import com.example.base.ViewModelFactoryModule
import com.example.data.di.DataModule
import com.example.domain.di.DomainModule
import com.example.tanorami.auth.login.di.LoginModule
import com.example.tanorami.auth.registration.di.RegistrationModule
import com.example.tanorami.base_build_hero.di.BaseBuildHeroModule
import com.example.tanorami.builds_hero_from_users.di.BuildsHeroFromUsersModule
import com.example.tanorami.change_nickname.di.ChangeNicknameModule
import com.example.tanorami.create_build_hero.di.CreateBuildHeroModule
import com.example.tanorami.create_build_heroes_list.di.CreateBuildHeroesListModule
import com.example.tanorami.createteam.di.CreateTeamModule
import com.example.tanorami.info_about_decoration.di.InfoAboutDecorationModule
import com.example.tanorami.info_about_hero.di.InfoAboutHeroModule
import com.example.tanorami.info_about_relic.di.InfoAboutRelicModule
import com.example.tanorami.info_about_weapon.di.InfoAboutWeaponModule
import com.example.tanorami.load_data.di.LoadDataModule
import com.example.tanorami.main.di.MainScreenModule
import com.example.tanorami.profile.di.ProfileModule
import com.example.tanorami.send_feedback.di.SendFeedbackModule
import com.example.tanorami.settings.di.SettingsModule
import com.example.tanorami.teams.di.TeamsFromUsersModule
import com.example.tanorami.viewing_users_build.di.ViewingBuildHeroFromUserModel
import dagger.Module

@Module(
    includes = [
        MainScreenModule::class,
        RepositoryModule::class,
        ApiModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelFactoryModule::class,
        ProfileModule::class,
        BuildsHeroFromUsersModule::class,
        TeamsFromUsersModule::class,
        InfoAboutHeroModule::class,
        LoadDataModule::class,
        MainScreenModule::class,
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
        CreateBuildHeroesListModule::class,
    ]
)
interface AppModule