package com.bumblebeeai.spire.common.di

import com.azmiradi.android_base.helpers.encryption.CryptoOperations
import com.azmiradi.android_base.helpers.encryption.EncryptedDataVerification
import com.azmiradi.android_base.helpers.encryption.KeyStoreManger
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.encryption.IEncryptedDataVerification
import com.azmiradi.kotlin_base.domain.encryption.IKeyStoreManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncryptionModule {
    @Provides
    @Singleton
    fun provideCryptoOperations(keyStoreManger: IKeyStoreManger): ICryptoOperations {
        return CryptoOperations(keyStoreManger)
    }

    @Provides
    @Singleton
    fun provideKeyStoreManger(): IKeyStoreManger {
        return KeyStoreManger()
    }

    @Provides
    @Singleton
    fun provideEncryptedDataVerification(): IEncryptedDataVerification {
        return EncryptedDataVerification()
    }
}