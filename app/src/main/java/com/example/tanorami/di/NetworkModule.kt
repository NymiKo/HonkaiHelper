package com.example.tanorami.di

import android.content.Context
import com.example.tanorami.activity.data.MainService
import com.example.tanorami.builds_hero_from_users.data.BuildsHeroListService
import com.example.tanorami.change_nickname.data.ChangeNicknameService
import com.example.tanorami.create_build_hero.data.CreateBuildHeroService
import com.example.tanorami.createteam.data.CreateTeamService
import com.example.tanorami.data.AuthInterceptor
import com.example.tanorami.heroes.data.HeroesListService
import com.example.tanorami.data.image_loader.ImageLoader
import com.example.tanorami.data.image_loader.ImageLoaderImpl
import com.example.tanorami.load_data.data.LoadDataService
import com.example.tanorami.auth.login.data.LoginService
import com.example.tanorami.profile.data.ProfileService
import com.example.tanorami.auth.registration.data.RegistrationService
import com.example.tanorami.send_feedback.data.SendFeedbackService
import com.example.tanorami.teams.data.TeamsListService
import com.example.tanorami.viewing_users_build.data.ViewingUsersBuildService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideAuthInterceptor(context: Context): AuthInterceptor = AuthInterceptor(context)

    @Provides
    @Singleton
    fun provideClient(logginInterceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://f0862137.xsph.ru")
        .client(okHttpClient)
        .build()

    @Provides
    fun provideImageLoader(context: Context, ioDispatcher: CoroutineDispatcher): ImageLoader = ImageLoaderImpl(context, ioDispatcher)

    @Provides
    fun providesHeroesListService(retrofit: Retrofit): HeroesListService = retrofit.create(HeroesListService::class.java)

    @Provides
    fun provideTeamsListService(retrofit: Retrofit): TeamsListService = retrofit.create(TeamsListService::class.java)

    @Provides
    fun provideCreateTeamService(retrofit: Retrofit): CreateTeamService = retrofit.create(CreateTeamService::class.java)

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Provides
    fun provideRegistrationService(retrofit: Retrofit): RegistrationService = retrofit.create(
        RegistrationService::class.java)

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService = retrofit.create(ProfileService::class.java)

    @Provides
    fun providesLoadDataService(retrofit: Retrofit): LoadDataService = retrofit.create(LoadDataService::class.java)

    @Provides
    fun providesMainService(retrofit: Retrofit): MainService = retrofit.create(MainService::class.java)

    @Provides
    fun providesBuildsHeroService(retrofit: Retrofit): BuildsHeroListService = retrofit.create(BuildsHeroListService::class.java)

    @Provides
    fun providesCreateBuildHeroService(retrofit: Retrofit): CreateBuildHeroService = retrofit.create(CreateBuildHeroService::class.java)

    @Provides
    fun providesViewingUsersBuildService(retrofit: Retrofit): ViewingUsersBuildService = retrofit.create(ViewingUsersBuildService::class.java)

    @Provides
    fun providesChangeNicknameService(retrofit: Retrofit): ChangeNicknameService = retrofit.create(ChangeNicknameService::class.java)

    @Provides
    fun providesSendFeedbackService(retrofit: Retrofit): SendFeedbackService = retrofit.create(SendFeedbackService::class.java)
}