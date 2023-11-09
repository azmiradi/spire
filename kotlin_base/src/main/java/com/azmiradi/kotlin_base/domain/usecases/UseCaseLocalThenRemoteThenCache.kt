package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

abstract class UseCaseLocalThenRemoteThenCache<Domain, in Body> :
    UseCaseRemoteThenCache<Domain, Body>() {

    protected abstract fun executeLocalDS(body: Body?): Flow<Domain>

    protected abstract fun performRemoteOperation(domain: Domain?): Boolean

    override operator fun invoke(body: Body?): Flow<Resource<Domain>> =
        channelFlow {
            // Run local first
            runFlow(executeLocalDS(body)) {
                send(it)
            }.collect { localData ->
                if (performRemoteOperation(localData)) { //call network and get result
                    runFlow(executeRemoteDS(body)) {
                        send(it)
                    }.collect {
                        if (performLocalOperation(it))
                            executeLocalOperation(it, body)

                        send(invokeSuccessState(it))
                    }
                } else { // get local
                    send(invokeSuccessState(localData))
                }
            }
        }
}