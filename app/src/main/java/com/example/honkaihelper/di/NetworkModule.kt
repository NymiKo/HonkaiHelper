package com.example.honkaihelper.di

import android.content.Context
import com.example.honkaihelper.createteam.data.CreateTeamService
import com.example.honkaihelper.data.AuthInterceptor
import com.example.honkaihelper.heroes.data.HeroesListService
import com.example.honkaihelper.login.data.LoginService
import com.example.honkaihelper.registration.data.RegistrationService
import com.example.honkaihelper.teams.data.TeamsListService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun providesHeroesListService(retrofit: Retrofit): HeroesListService = retrofit.create(HeroesListService::class.java)

    @Provides
    fun provideTeamsListService(retrofit: Retrofit): TeamsListService = retrofit.create(TeamsListService::class.java)

    @Provides
    fun provideCreateTeamService(retrofit: Retrofit): CreateTeamService = retrofit.create(CreateTeamService::class.java)

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Provides
    fun provideRegistrationService(retrofit: Retrofit): RegistrationService = retrofit.create(RegistrationService::class.java)
}