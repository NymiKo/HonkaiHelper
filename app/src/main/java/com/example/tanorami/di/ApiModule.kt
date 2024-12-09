package com.example.tanorami.di

import android.content.Context
import com.example.data.remote.api.build.HeroBuildsFromUsersApi
import com.example.data.remote.api.stat.StatApi
import com.example.data.remote.api.team.TeamsFromUsersApi
import com.example.domain.di.DispatcherIo
import com.example.tanorami.auth.login.data.LoginService
import com.example.tanorami.auth.registration.data.RegistrationService
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListService
import com.example.tanorami.change_nickname.data.ChangeNicknameService
import com.example.tanorami.create_build_hero.data.CreateBuildHeroService
import com.example.tanorami.createteam.data.CreateTeamService
import com.example.tanorami.load_data.data.FileManager
import com.example.tanorami.load_data.data.FileManagerImpl
import com.example.tanorami.load_data.data.LoadDataService
import com.example.tanorami.main.data.MainScreenService
import com.example.tanorami.profile.data.ProfileService
import com.example.tanorami.send_feedback.data.SendFeedbackService
import com.example.tanorami.teams.data.TeamsFromUsersService
import com.example.tanorami.viewing_users_build.data.ViewingBuildHeroFromUserService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object ApiModule {
    @Provides
    fun provideImageLoader(
        context: Context,
        okHttpClient: OkHttpClient,
        @DispatcherIo ioDispatcher: CoroutineDispatcher,
    ): FileManager = FileManagerImpl(context, okHttpClient, ioDispatcher)

    @Provides
    fun provideTeamsListService(retrofit: Retrofit): TeamsFromUsersService =
        retrofit.create(TeamsFromUsersService::class.java)

    @Provides
    fun provideCreateTeamService(retrofit: Retrofit): CreateTeamService =
        retrofit.create(CreateTeamService::class.java)

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    fun provideRegistrationService(retrofit: Retrofit): RegistrationService =
        retrofit.create(
            RegistrationService::class.java
        )

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Provides
    fun providesLoadDataService(retrofit: Retrofit): LoadDataService =
        retrofit.create(LoadDataService::class.java)

    @Provides
    fun providesBuildsHeroService(retrofit: Retrofit): BuildsHeroListService =
        retrofit.create(BuildsHeroListService::class.java)

    @Provides
    fun providesCreateBuildHeroService(retrofit: Retrofit): CreateBuildHeroService =
        retrofit.create(CreateBuildHeroService::class.java)

    @Provides
    fun providesViewingUsersBuildService(retrofit: Retrofit): ViewingBuildHeroFromUserService =
        retrofit.create(ViewingBuildHeroFromUserService::class.java)

    @Provides
    fun providesChangeNicknameService(retrofit: Retrofit): ChangeNicknameService =
        retrofit.create(ChangeNicknameService::class.java)

    @Provides
    fun providesSendFeedbackService(retrofit: Retrofit): SendFeedbackService =
        retrofit.create(SendFeedbackService::class.java)

    @Provides
    fun providesMainScreenService(retrofit: Retrofit): MainScreenService =
        retrofit.create(MainScreenService::class.java)

    @Provides
    fun providesHeroBuildsFromUserApi(retrofit: Retrofit): HeroBuildsFromUsersApi =
        retrofit.create(HeroBuildsFromUsersApi::class.java)

    @Provides
    fun providesTeamsFromUsersApi(retrofit: Retrofit): TeamsFromUsersApi =
        retrofit.create(TeamsFromUsersApi::class.java)

    @Provides
    fun providesStatApi(retrofit: Retrofit): StatApi = retrofit.create(StatApi::class.java)
}