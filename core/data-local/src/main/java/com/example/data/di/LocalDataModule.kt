package com.example.data.di

import dagger.Module

@Module(includes = [RoomModule::class, DataStoreModule::class, LocalDataSourceModule::class])
interface LocalDataModule