package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

abstract class UseCaseRemoteThenCache<Domain, in Body> : UseCaseRemote<Domain, Body>() {

    override operator fun invoke(body: Body?): Flow<Resource<Domain>> =
        channelFlow {
            runFlow(executeRemoteDS(body)) {
                send(it)
            }.collect {
                if (performLocalOperation(it))
                    executeLocalOperation(it, body)

                send(invokeSuccessState(it))
            }
        }

    protected abstract fun performLocalOperation(domain: Domain): Boolean

    protected abstract suspend fun executeLocalOperation(domain: Domain, body: Body?)
}