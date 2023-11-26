package com.bumblebeeai.spire

import com.azmiradi.android_base.configuration.AppConfiguration
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.common.domain.model.enums.CommenKeyValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltAndroidTest
class IKeyStorageTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var storageKeyValue: IStorageKeyValue

    @Inject
    lateinit var cryptoOperations: ICryptoOperations

    @Before
    fun init() {
        AppConfiguration.inti(
            localStorageName = "Spire",
            baseURL = BuildConfig.BASE_URL,
            connectTimeoutSec = 5
        )
        hiltRule.inject()
    }

    @Test
    fun testStorage() = runTest {
        val dataToEncrypt = "APP Egypt"
        storageKeyValue.saveSecuredValue(CommenKeyValue.TOKEN, CommenKeyValue.TOKEN,dataToEncrypt.toByteArray())
        val getEd = storageKeyValue.getSecuredValue(CommenKeyValue.TOKEN,CommenKeyValue.TOKEN)
        assertThat(dataToEncrypt).isEqualTo(String(getEd))
    }
}
