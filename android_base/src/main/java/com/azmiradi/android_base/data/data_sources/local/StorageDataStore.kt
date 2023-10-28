package com.azmiradi.android_base.data.data_sources.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.azmiradi.android_base.configuration.Configuration
import com.azmiradi.android_base.helpers.io.DataStoreUtil
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.StorageKey
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.StorageKeyValue
import com.azmiradi.kotlin_base.helpers.ICryptoOperations
import com.azmiradi.kotlin_base.utilities.extensions.getModelFromJSON
import com.azmiradi.kotlin_base.utilities.extensions.toJson
import java.lang.reflect.Type


class StorageDataStore(
    dataStore: DataStore<Preferences>,
    cryptoOperation: ICryptoOperations,
) : StorageKeyValue,
    DataStoreUtil(
        dataStore,
        cryptoOperation
    ) {

    override suspend fun getSecuredValue(storageKey: StorageKey, keyAlias: String): ByteArray {
        TODO("Not yet implemented")
    }

    override suspend fun saveSecuredValue(
        storageKey: StorageKey,
        keyAlias: String,
        value: ByteArray,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getString(storageKey: StorageKey): String {
        TODO("Not yet implemented")
    }

    override suspend fun saveString(storageKey: StorageKey, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearString(storageKey: StorageKey) {
        TODO("Not yet implemented")
    }

    override suspend fun getInt(storageKey: StorageKey): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun saveInt(storageKey: StorageKey, value: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun clearInt(storageKey: StorageKey) {
        TODO("Not yet implemented")
    }

    override suspend fun getBoolean(storageKey: StorageKey): Boolean? {
        TODO("Not yet implemented")
    }

    override suspend fun saveBoolean(storageKey: StorageKey, value: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun <Model> getModelBasedOnType(
        storageKey: StorageKey,
        tokenType: Type,
    ): Model? {
        return getString(stringPreferencesKey(storageKey.keyValue))?.getModelFromJSON(tokenType)
    }

    override suspend fun <Model> saveModelBasedOnType(storageKey: StorageKey, value: Model) {
        setString(stringPreferencesKey(storageKey.keyValue), value.toJson())
    }

    override suspend fun hasKey(storageKey: StorageKey): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllEntries() {
        TODO("Not yet implemented")
    }
}