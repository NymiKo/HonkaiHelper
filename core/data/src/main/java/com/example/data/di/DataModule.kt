package com.example.data.di

import dagger.Module

@Module(includes = [LocalDataModule::class, RemoteDataModule::class, RepositoryModule::class])
interface DataModule