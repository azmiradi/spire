package com.bumblebeeai.spire.auth.login.data.mapper

import com.azmiradi.kotlin_base.domain.mapper.Mapper
import com.bumblebeeai.spire.auth.login.data.model.dto.LoginDto
import com.bumblebeeai.spire.auth.login.domain.models.Login

object LoginMapper : Mapper<LoginDto, Login>() {
    override fun dtoToDomain(model: LoginDto): Login {
        return Login(
            accessToken = model.accessToken,
            merchantId = model.merchantId
        )
    }
}