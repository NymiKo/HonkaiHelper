package com.example.profile.di

import com.example.data.source.profile.ProfileRemoteDataSource
import com.example.domain.data_store.AppDataStore
import com.example.domain.usecase.profile.GetProfileUseCase
import com.example.domain.usecase.profile.LogoutAccountUseCase
import kotlinx.coroutines.CoroutineDispatcher

interface ProfileComponentDependencies {
    val getProfileRemoteDataSource: ProfileRemoteDataSource
    val getGetProfileUseCase: GetProfileUseCase
    val getLogoutAccountUseCase: LogoutAccountUseCase
    val appDataStore: AppDataStore
    val getIoDispatcher: CoroutineDispatcher
}