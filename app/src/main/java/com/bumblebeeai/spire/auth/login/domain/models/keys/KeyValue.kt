package com.bumblebeeai.spire.auth.login.domain.models.keys

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IKeyValue

enum class KeyValue : IKeyValue {
    TOKEN {
        override val keyValue: String
            get() = "token"
    },
    LOGIN_INFO {
        override val keyValue: String
            get() = "login_info"
    },

    MERCHANT_ID {
        override val keyValue: String
            get() = "merchantId"
    }
}