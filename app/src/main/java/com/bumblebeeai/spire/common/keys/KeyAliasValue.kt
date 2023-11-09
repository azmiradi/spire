package com.bumblebeeai.spire.common.keys

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IKeyValue

enum class KeyAliasValue : IKeyValue {
    TOKEN {
        override val keyValue: String
            get() = "token"
    }
}