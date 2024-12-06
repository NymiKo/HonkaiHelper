package com.example.tanorami.di

import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.login.domain.LoginRepositoryImpl
import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.auth.registration.data.RegistrationRepositoryImpl
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepository
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepositoryImpl
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListRepository
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListRepositoryImpl
import com.example.tanorami.change_nickname.data.ChangeNicknameRepository
import com.example.tanorami.change_nickname.data.ChangeNicknameRepositoryImpl
import com.example.tanorami.create_build_hero.data.CreateBuildHeroRepository
import com.example.tanorami.create_build_hero.data.CreateBuildHeroRepositoryImpl
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.data.CreateTeamRepositoryImpl
import com.example.tanorami.info_about_decoration.data.InfoAboutDecorationRepository
import com.example.tanorami.info_about_decoration.data.InfoAboutDecorationRepositoryImpl
import com.example.tanorami.info_about_hero.data.InfoAboutHeroRepository
import com.example.tanorami.info_about_hero.data.InfoAboutHeroRepositoryImpl
import com.example.tanorami.info_about_relic.data.InfoAboutRelicRepository
import com.example.tanorami.info_about_relic.data.InfoAboutRelicRepositoryImpl
import com.example.tanorami.info_about_weapon.data.InfoAboutWeaponRepository
import com.example.tanorami.info_about_weapon.data.InfoAboutWeaponRepositoryImpl
import com.example.tanorami.load_data.data.LoadDataRepository
import com.example.tanorami.load_data.data.LoadDataRepositoryImpl
import com.example.tanorami.main.data.MainScreenRepository
import com.example.tanorami.main.data.MainScreenRepositoryImpl
import com.example.tanorami.profile.domain.ProfileRepository
import com.example.tanorami.profile.domain.ProfileRepositoryImpl
import com.example.tanorami.send_feedback.data.SendFeedbackRepository
import com.example.tanorami.send_feedback.data.SendFeedbackRepositoryImpl
import com.example.tanorami.settings.data.SettingsRepository
import com.example.tanorami.settings.data.SettingsRepositoryImpl
import com.example.tanorami.teams.data.TeamsFromUsersRepository
import com.example.tanorami.teams.data.TeamsFromUsersRepositoryImpl
import com.example.tanorami.viewing_users_build.data.ViewingBuildHeroFromUserRepository
import com.example.tanorami.viewing_users_build.data.ViewingBuildHeroFromUserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindTeamsListRepository(repository: TeamsFromUsersRepositoryImpl): TeamsFromUsersRepository

    @Singleton
    @Binds
    fun bindCreateTeamRepository(repository: CreateTeamRepositoryImpl): CreateTeamRepository

    @Singleton
    @Binds
    fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    fun bindRegistrationRepository(repository: RegistrationRepositoryImpl): RegistrationRepository

    @Singleton
    @Binds
    fun bindProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository

    @Singleton
    @Binds
    fun bindInfoAboutHeroRepository(repository: InfoAboutHeroRepositoryImpl): InfoAboutHeroRepository

    @Singleton
    @Binds
    fun bindLoadDataRepository(repository: LoadDataRepositoryImpl): LoadDataRepository

    @Singleton
    @Binds
    fun bindBaseBuildHeroRepository(repository: BaseBuildHeroRepositoryImpl): BaseBuildHeroRepository

    @Singleton
    @Binds
    fun bindWeaponInfoRepository(repository: InfoAboutWeaponRepositoryImpl): InfoAboutWeaponRepository

    @Singleton
    @Binds
    fun bindRelicInfoRepository(repository: InfoAboutRelicRepositoryImpl): InfoAboutRelicRepository

    @Singleton
    @Binds
    fun bindDecorationInfoRepository(repository: InfoAboutDecorationRepositoryImpl): InfoAboutDecorationRepository

    @Singleton
    @Binds
    fun bindBuildsHeroRepository(repository: BuildsHeroListRepositoryImpl): BuildsHeroListRepository

    @Singleton
    @Binds
    fun bindCreateBuildHeroRepository(repository: CreateBuildHeroRepositoryImpl): CreateBuildHeroRepository

    @Singleton
    @Binds
    fun bindViewingUsersBuildRepository(repository: ViewingBuildHeroFromUserRepositoryImpl): ViewingBuildHeroFromUserRepository

    @Singleton
    @Binds
    fun bindChangeNicknameRepository(repository: ChangeNicknameRepositoryImpl): ChangeNicknameRepository

    @Singleton
    @Binds
    fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository

    @Singleton
    @Binds
    fun bindSendFeedbackRepository(repository: SendFeedbackRepositoryImpl): SendFeedbackRepository

    @Singleton
    @Binds
    fun bindMainScreenRepository(repository: MainScreenRepositoryImpl): MainScreenRepository
}