package com.azmiradi.android_base.data.data_sources.local

import android.content.Context
import android.content.SharedPreferences
import com.azmiradi.android_base.configuration.Configuration
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.StorageKey
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.StorageKeyValue
import com.azmiradi.kotlin_base.utilities.extensions.getModelFromJSON
import com.azmiradi.kotlin_base.utilities.extensions.toJson
import java.lang.reflect.Type

class StoragdsfdseKeyValue(private val context: Context) : StorageKeyValue {

    private val keyValueStorge: SharedPreferences =
        context.getSharedPreferences(Configuration.localStorageName, Context.MODE_PRIVATE)

    override suspend fun getSecuredValue(storageKey: StorageKey, keyAlias: String): ByteArray {
        TODO()
    }

    override suspend fun saveSecuredValue(
        storageKey: StorageKey,
        keyAlias: String,
        value: ByteArray,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getString(storageKey: StorageKey): String {
        TODO()
    }

    override suspend fun saveString(storageKey: StorageKey, value: String) {

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
        TODO()
    }

    override suspend fun <Model> saveModelBasedOnType(storageKey: StorageKey, value: Model) {
        TODO()
    }

    override suspend fun hasKey(storageKey: StorageKey): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllEntries() {
        TODO("Not yet implemented")
    }
}