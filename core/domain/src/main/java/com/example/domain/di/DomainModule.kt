package com.example.domain.di

import dagger.Module

@Module(includes = [DispatcherModule::class, UseCaseModule::class])
interface DomainModule