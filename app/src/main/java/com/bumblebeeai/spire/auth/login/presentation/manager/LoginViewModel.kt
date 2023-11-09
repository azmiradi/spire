package com.bumblebeeai.spire.auth.login.presentation.manager

import androidx.lifecycle.viewModelScope
import com.azmiradi.android_base.presentation.StateViewModel
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.auth.login.domain.models.Login
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.usecases.LoginUseCase
import com.bumblebeeai.spire.common.keys.KeyAliasValue
import com.bumblebeeai.spire.common.keys.KeyValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val storageKeyValue: IStorageKeyValue,
) :
    StateViewModel<Login>() {

    fun login(loginRequest: LoginRequest) {
        emitData(loginUseCase.invoke(loginRequest))
    }

    fun savePhone(phone: String) {
        viewModelScope.launch {
            storageKeyValue.saveSecuredValue(
                KeyValue.TOKEN,
                KeyAliasValue.TOKEN,
                phone.encodeToByteArray(),
                authenticationRequired = true
            )
        }
    }

    fun readPhone() {
        viewModelScope.launch {
            val phone = storageKeyValue.getSecuredValue(
                KeyValue.TOKEN,
                KeyAliasValue.TOKEN
            )
            println("phone: " + phone.decodeToString())
        }
    }
}