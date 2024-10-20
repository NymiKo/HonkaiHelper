package com.example.tanorami.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.tanorami.data.data_store.AppDataStore
import com.example.tanorami.data.data_store.AppDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
interface DataStoreModule {

    companion object {
        @Provides
        @Singleton
        fun providesDataStore(
            appContext: Context,
            @IODispatcher dispatcherIO: CoroutineDispatcher
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                migrations = listOf(
                    SharedPreferencesMigration(
                        context = appContext,
                        sharedPreferencesName = "USER",
                        keysToMigrate = setOf("token")
                    )
                ),
                scope = CoroutineScope(dispatcherIO + SupervisorJob()),
                produceFile = { appContext.preferencesDataStoreFile("APP_DATA_STORE") }
            )
        }
    }

    @Binds
    @Singleton
    fun bindUserDataStore(userDataStore: AppDataStoreImpl): AppDataStore
}