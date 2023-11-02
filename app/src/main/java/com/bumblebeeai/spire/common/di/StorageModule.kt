package com.bumblebeeai.spire.common.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.azmiradi.android_base.configuration.AppConfiguration
import com.azmiradi.android_base.data.data_sources.local.StorageKeyValue
import com.azmiradi.android_base.helpers.encryption.CryptoOperations
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.myDataStore by preferencesDataStore(AppConfiguration.localStorageName)

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideStorageKeyValue(
        @ApplicationContext context: Context,
        cryptoOperations: ICryptoOperations,
    ): IStorageKeyValue {
        return StorageKeyValue(context.myDataStore, cryptoOperations)
    }
}