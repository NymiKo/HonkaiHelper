package com.example.honkaihelper.di

import com.example.honkaihelper.activity.data.MainRepository
import com.example.honkaihelper.activity.data.MainRepositoryImpl
import com.example.honkaihelper.base_build_hero.data.BaseBuildHeroRepository
import com.example.honkaihelper.base_build_hero.data.BaseBuildHeroRepositoryImpl
import com.example.honkaihelper.info_about_hero.data.InfoAboutHeroRepository
import com.example.honkaihelper.info_about_hero.data.InfoAboutHeroRepositoryImpl
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.data.CreateTeamRepositoryImpl
import com.example.honkaihelper.equipment.data.EquipmentRepository
import com.example.honkaihelper.equipment.data.EquipmentRepositoryImpl
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.data.HeroesListRepositoryImpl
import com.example.honkaihelper.load_data.data.LoadDataRepository
import com.example.honkaihelper.load_data.data.LoadDataRepositoryImpl
import com.example.honkaihelper.login.data.LoginRepository
import com.example.honkaihelper.login.data.LoginRepositoryImpl
import com.example.honkaihelper.profile.data.ProfileRepository
import com.example.honkaihelper.profile.data.ProfileRepositoryImpl
import com.example.honkaihelper.registration.data.RegistrationRepository
import com.example.honkaihelper.registration.data.RegistrationRepositoryImpl
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.data.TeamsListRepositoryImpl
import com.example.honkaihelper.weapon.data.WeaponInfoRepository
import com.example.honkaihelper.weapon.data.WeaponInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindHeroesListRepository(repository: HeroesListRepositoryImpl): HeroesListRepository

    @Singleton
    @Binds
    fun bindTeamsListRepository(repository: TeamsListRepositoryImpl): TeamsListRepository

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
    fun bindEquipmentRepository(repository: EquipmentRepositoryImpl): EquipmentRepository

    @Singleton
    @Binds
    fun bindInfoAboutHeroRepository(repository: InfoAboutHeroRepositoryImpl): InfoAboutHeroRepository

    @Singleton
    @Binds
    fun bindLoadDataRepository(repository: LoadDataRepositoryImpl): LoadDataRepository

    @Singleton
    @Binds
    fun bindMainRepository(repository: MainRepositoryImpl): MainRepository

    @Singleton
    @Binds
    fun bindBaseBuildHeroRepository(repository: BaseBuildHeroRepositoryImpl): BaseBuildHeroRepository

    @Singleton
    @Binds
    fun bindWeaponInfoRepository(repository: WeaponInfoRepositoryImpl): WeaponInfoRepository

}