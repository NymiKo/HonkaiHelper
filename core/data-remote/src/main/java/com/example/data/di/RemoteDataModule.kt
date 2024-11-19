package com.example.data.di

import dagger.Module

@Module(includes = [RetrofitModule::class, RemoteDataSourceModule::class])
interface RemoteDataModule