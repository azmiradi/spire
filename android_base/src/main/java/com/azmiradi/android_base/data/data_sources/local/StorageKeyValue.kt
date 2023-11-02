package com.azmiradi.android_base.data.data_sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.azmiradi.android_base.R
import com.azmiradi.android_base.helpers.encryption.CryptoOperations
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.StorageKey
import com.azmiradi.kotlin_base.utilities.extensions.decodeFromBase64
import com.azmiradi.kotlin_base.utilities.extensions.encodeToBase64
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class StorageKeyValue(
   private val dataStore: DataStore<Preferences>,
   private val cryptoOperations: ICryptoOperations,
) : IStorageKeyValue() {

   override suspend fun saveSecuredValue(
      storageKey: StorageKey,
      keyAlias: String,
      value: ByteArray,
   ) {
      dataStore.secureEdit(keyAlias, value) { preference, byteArray ->
         preference[stringPreferencesKey(storageKey.keyValue)] = byteArray.encodeToBase64()
      }
   }

   override suspend fun getSecuredValue(storageKey: StorageKey, keyAlias: String): ByteArray {
      return dataStore.data.secureMap(keyAlias, fetchValue = { value ->
         value[stringPreferencesKey(storageKey.keyValue)]
      }).firstOrNull()
         ?: throw BaseException.Local.NotFoundData(R.string.error_io_unexpected_message)
   }


   /////////////////////////////////////////////////////////////

   private suspend inline fun DataStore<Preferences>.secureEdit(
      keyAlias: String, value: ByteArray,
      crossinline editStore: (MutablePreferences, ByteArray) -> Unit,
   ) = edit {
      val encryptedValue = cryptoOperations.encryptDataWithAES(keyAlias, value)
      editStore.invoke(it, encryptedValue)
   }

   /**
    * fetches encrypted data from DataStore
    * performs decryption
    * deserializes data into respective data type
    */
   private inline fun Flow<Preferences>.secureMap(
      keyAlias: String, crossinline fetchValue: (value: Preferences) -> String?,
   ): Flow<ByteArray?> = map { prefs ->
      if (fetchValue(prefs).isNullOrEmpty()) null
      else cryptoOperations.encryptDataWithAES(keyAlias, fetchValue(prefs)!!.decodeFromBase64())
   }
}