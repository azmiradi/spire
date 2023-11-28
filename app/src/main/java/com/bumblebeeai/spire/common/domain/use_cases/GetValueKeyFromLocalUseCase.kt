package com.bumblebeeai.spire.common.domain.use_cases

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IKeyValue
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.domain.usecases.UseCaseLocal
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetValueKeyFromLocalUseCase @Inject constructor(
    private val iStorageKeyValue: IStorageKeyValue,
) : UseCaseLocal<String, IKeyValue>() {
    override fun executeLocalDS(body: IKeyValue?) = flow {
        emit(iStorageKeyValue.getString(body!!))
    }

}