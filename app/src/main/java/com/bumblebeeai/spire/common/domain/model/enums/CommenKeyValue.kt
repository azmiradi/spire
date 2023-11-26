package com.bumblebeeai.spire.common.domain.model.enums

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IKeyValue

enum class CommenKeyValue : IKeyValue {
    TOKEN {
        override val keyValue: String
            get() = "token"
    },
    MERCHANT_ID {
        override val keyValue: String
            get() = "merchantId"
    }
}