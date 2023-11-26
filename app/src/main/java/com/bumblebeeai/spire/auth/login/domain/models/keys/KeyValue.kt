package com.bumblebeeai.spire.auth.login.domain.models.keys

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IKeyValue

enum class KeyValue : IKeyValue {
    LOGIN_INFO {
        override val keyValue: String
            get() = "login_info"
    }
}