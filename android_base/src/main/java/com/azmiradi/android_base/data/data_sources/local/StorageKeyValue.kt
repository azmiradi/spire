package com.azmiradi.android_base.data.data_sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.azmiradi.android_base.R
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IKeyValue
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject


class StorageKeyValue @Inject constructor(
   private val dataStore: DataStore<Preferences>,
   private val cryptoOperations: ICryptoOperations,
) : IStorageKeyValue() {

   override suspend fun saveString(storageKey: IKeyValue, value: String) {
      dataStore.edit {
         it[stringPreferencesKey(storageKey.keyValue)] = value
      }
   }

   override suspend fun getString(storageKey: IKeyValue): String {
      return dataStore.data.firstOrNull()?.get(stringPreferencesKey(storageKey.keyValue))
         ?: throw BaseException.Local.NotFoundData(R.string.error_io_unexpected_message)
   }

   override suspend fun saveSecuredValue(
      storageKey: IKeyValue,
      keyAlias: IKeyValue,
      value: ByteArray,
      authenticationRequired: Boolean,
      keyValidityEnd: Date?,
   ) {
      dataStore.secureEdit(
         keyAlias.keyValue,
         value,
         authenticationRequired = authenticationRequired,
         keyValidityEnd = keyValidityEnd
      ) { preference, byteArray ->
         preference[stringPreferencesKey(storageKey.keyValue)] =
            String(byteArray)
      }
   }

   override suspend fun getSecuredValue(
      storageKey: IKeyValue,
      keyAlias: IKeyValue,
   ): ByteArray {
      return dataStore.data.secureMap(keyAlias.keyValue,
         fetchValue = { value ->
            value[stringPreferencesKey(storageKey.keyValue)]
         }).firstOrNull()
         ?: throw BaseException.Local.NotFoundData(R.string.error_io_unexpected_message)
   }

   /////////////////////////////////////////////////////////////

   private suspend inline fun DataStore<Preferences>.secureEdit(
      keyAlias: String,
      value: ByteArray,
      authenticationRequired: Boolean = false,
      keyValidityEnd: Date? = null,
      crossinline editStore: (MutablePreferences, ByteArray) -> Unit,
   ) = edit {
      val encryptedValue = cryptoOperations.encryptDataWithAES(
         keyAlias = keyAlias,
         data = value,
         authenticationRequired = authenticationRequired,
         keyValidityEnd = keyValidityEnd
      )

      editStore.invoke(it, encryptedValue)
   }

   /**
    * fetches encrypted data from DataStore
    * performs decryption
    * deserializes data into respective data type
    */
   private inline fun Flow<Preferences>.secureMap(
      keyAlias: String,
      crossinline fetchValue: (value: Preferences) -> String?,
   ): Flow<ByteArray?> = map { prefs ->
      if (fetchValue(prefs).isNullOrEmpty()) null
      else {
         cryptoOperations.decryptDataWithAES(
            keyAlias = keyAlias,
            data = fetchValue(prefs)?.toByteArray()!!
         )
      }
   }

   override suspend fun hasKey(storageKey: IKeyValue): Boolean {
      return dataStore.data.map { preference ->
         preference.contains(key = stringPreferencesKey(storageKey.keyValue))
      }.first()
   }
}