package com.azmiradi.android_base.helpers.io

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.azmiradi.kotlin_base.helpers.ICryptoOperations
import com.azmiradi.kotlin_base.utilities.extensions.decodeFromBase64
import com.azmiradi.kotlin_base.utilities.extensions.encodeToBase64
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

abstract class DataStoreUtil(
    private val dataStore: DataStore<Preferences>,
    private val cryptoOperation: ICryptoOperations
) {

    // ----------------------------------------- String --------------------------------------------

    protected suspend fun getSecuredData(
        key: Preferences.Key<String>, keyAlias: String
    ): ByteArray? {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.secureMap(keyAlias) {
            it[key]
        }.firstOrNull()
    }

    protected suspend fun setSecuredData(
        key: Preferences.Key<String>, keyAlias: String, value: ByteArray
    ) {
        dataStore.secureEdit(keyAlias, value) { prefs, encryptedValue ->
            prefs[key] = encryptedValue.encodeToBase64()
        }
    }

    protected suspend fun clearSecuredData(key: Preferences.Key<String>) {
        dataStore.edit { it[key] = "" }
    }

    // ----------------------------------------- String --------------------------------------------

    protected suspend fun getString(key: Preferences.Key<String>): String? = dataStore.data.catch {
        emit(emptyPreferences())
    }.map { it[key] }.firstOrNull()

    protected suspend fun setString(key: Preferences.Key<String>, value: String): Boolean {
        return dataStore.edit { prefs -> prefs[key] = value }.contains(key)
    }

    suspend fun hasKey(key: Preferences.Key<*>): Boolean {
        var hasKey = false
        dataStore.edit { hasKey = it.contains(key) }
        return hasKey
    }

    suspend fun clearDataStore() {
        dataStore.edit { it.clear() }
    }

    // ----------------------------------------- Settings ------------------------------------------

    /**
     * serializes data type into string
     * performs encryption
     * stores encrypted data in DataStore
     */
    private suspend inline fun DataStore<Preferences>.secureEdit(
        keyAlias: String, value: ByteArray,
        crossinline editStore: (MutablePreferences, ByteArray) -> Unit
    ) = edit {
        val encryptedValue = cryptoOperation.encryptAES(keyAlias, value)
        editStore.invoke(it, encryptedValue)
    }

    /**
     * fetches encrypted data from DataStore
     * performs decryption
     * deserializes data into respective data type
     */
    private inline fun Flow<Preferences>.secureMap(
        keyAlias: String, crossinline fetchValue: (value: Preferences) -> String?
    ): Flow<ByteArray?> = map { prefs ->
        if (fetchValue(prefs).isNullOrEmpty()) null
        else cryptoOperation.decryptAES(keyAlias, fetchValue(prefs)!!.decodeFromBase64())
    }
}