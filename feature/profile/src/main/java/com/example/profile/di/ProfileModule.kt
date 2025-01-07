package com.example.profile.di

import com.example.di.scopes.FeatureScope
import com.example.profile.data.ProfileRepositoryImpl
import com.example.profile.domain.ProfileRepository
import dagger.Binds
import dagger.Module

@Module
interface ProfileModule {
    @Binds
    @FeatureScope
    fun provideProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository
}